package com.plannora.booking_service.security;

import com.plannora.booking_service.security.RequestContext;
import com.plannora.booking_service.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;   // ✅ ADD
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j   // ✅ ADD
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            Claims claims = jwtUtils.validateToken(token);

            String email = claims.getSubject();
            Long userId = Long.valueOf(claims.get("user_id", String.class));
            String role = claims.get("user_role", String.class);

            // ===================== SPRING SECURITY =====================
            SimpleGrantedAuthority authority =
                    new SimpleGrantedAuthority(role);

            UserPrincipal principal = new UserPrincipal(
                    userId,
                    email,
                    List.of(authority),
                    role
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            principal.getAuthorities()
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            // ✅ ADD LOGS (VERY IMPORTANT)
            log.info("JWT USER: {}", email);
            log.info("JWT ROLE: {}", role);
            log.info("SPRING AUTHORITIES: {}", authentication.getAuthorities());

            // ===================== REQUEST CONTEXT =====================
            RequestContext context = new RequestContext();
            context.setUserId(userId);
            context.setEmail(email);
            context.setRole(role);
            RequestContext.set(context);

        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            RequestContext.clear();
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            RequestContext.clear();
        }
    }
}
