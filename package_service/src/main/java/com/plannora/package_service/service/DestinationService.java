package com.plannora.package_service.service;

import com.plannora.package_service.dto.request.DestinationRequestDTO;
import com.plannora.package_service.utils.BaseResponse;

public interface DestinationService {

    BaseResponse create(DestinationRequestDTO dto);

    BaseResponse getAll();
}
