package com.plannora.package_service.service.Impl;

import com.plannora.package_service.dto.request.PackageRequestDTO;
import com.plannora.package_service.dto.response.PackageResponseDTO;
import com.plannora.package_service.entity.Destination;
import com.plannora.package_service.entity.TravelPackage;
import com.plannora.package_service.repository.DestinationRepo;
import com.plannora.package_service.repository.PackageRepo;
import com.plannora.package_service.service.PackageService;
import com.plannora.package_service.utils.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.plannora.package_service.utils.GsUtils.error;

@Service
@RequiredArgsConstructor
@Transactional
public class PackageServiceImpl implements PackageService {

    private final PackageRepo packageRepo;
    private final DestinationRepo destinationRepo;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse create(PackageRequestDTO dto) {

        Optional<Destination> destination =
                destinationRepo.findById(dto.getDestinationId());

        if (destination.isEmpty()) {
            return error(
                    HttpStatus.NOT_FOUND,
                    ErrorCode.NO_DATA_FOUND,
                    Errors.ERROR_TYPE.DESTINATION
            );
        }

        TravelPackage travelPackage =
                modelMapper.map(dto, TravelPackage.class);

        travelPackage.setDestination(destination.get());
        packageRepo.save(travelPackage);

        return GsUtils.getBaseResponse(
                HttpStatus.CREATED,
                "Package created successfully"
        );
    }

    @Override
    public BaseResponse getAll() {

        List<PackageResponseDTO> list =
                packageRepo.findAll()
                        .stream()
                        .map(pkg -> {
                            PackageResponseDTO dto =
                                    modelMapper.map(pkg, PackageResponseDTO.class);
                            dto.setDestinationId(pkg.getDestination().getId());
                            dto.setDestinationCity(pkg.getDestination().getCity());
                            dto.setDestinationPlaceName(
                                    pkg.getDestination().getPlaceName());
                            return dto;
                        })
                        .toList();

        return GsUtils.getBaseResponse(HttpStatus.OK, list);
    }

    @Override
    public BaseResponse getById(Long id) {

        Optional<TravelPackage> pkg = packageRepo.findById(id);

        if (pkg.isEmpty()) {
            return error(
                    HttpStatus.NOT_FOUND,
                    ErrorCode.NO_DATA_FOUND,
                    Errors.ERROR_TYPE.PACKAGE
            );
        }

        PackageResponseDTO dto =
                modelMapper.map(pkg.get(), PackageResponseDTO.class);

        dto.setDestinationId(pkg.get().getDestination().getId());
        dto.setDestinationCity(pkg.get().getDestination().getCity());
        dto.setDestinationPlaceName(
                pkg.get().getDestination().getPlaceName());

        return GsUtils.getBaseResponse(HttpStatus.OK, dto);
    }
}
