package com.plannora.package_service.service;

import com.plannora.package_service.dto.request.ItineraryRequestDTO;
import com.plannora.package_service.utils.BaseResponse;

public interface ItineraryService {

    BaseResponse addItinerary(Long packageId, ItineraryRequestDTO dto);

    BaseResponse getByPackageId(Long packageId);
}
