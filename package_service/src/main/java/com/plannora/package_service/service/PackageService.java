package com.plannora.package_service.service;

import com.plannora.package_service.dto.request.PackageRequestDTO;
import com.plannora.package_service.utils.BaseResponse;

public interface PackageService {

    BaseResponse create(PackageRequestDTO dto);

    BaseResponse getAll();

    BaseResponse getById(Long id);
}
