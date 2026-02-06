package com.plannora.user_service.service;

import com.plannora.user_service.dto.request.*;
import com.plannora.user_service.dto.request.ChangePasswordDTO;
import com.plannora.user_service.dto.request.UserRegisterDTO;
import com.plannora.user_service.dto.request.UserSignInDTO;
import com.plannora.user_service.dto.request.UserUpdateDTO;
import com.plannora.user_service.utils.BaseResponse;
import jakarta.validation.Valid;

public interface UserService {
    BaseResponse add(UserRegisterDTO user);



    BaseResponse userSignIn(@Valid UserSignInDTO request);


    BaseResponse getMyProfile();

    BaseResponse updateMyProfile(@Valid UserUpdateDTO request);

    BaseResponse changePassword(@Valid ChangePasswordDTO request);




}
