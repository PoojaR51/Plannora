package com.plannora.package_service.service.Impl;

import com.plannora.package_service.dto.request.ItineraryRequestDTO;
import com.plannora.package_service.dto.response.ItineraryResponseDTO;
import com.plannora.package_service.entity.Itinerary;
import com.plannora.package_service.entity.TravelPackage;
import com.plannora.package_service.repository.ItineraryRepo;
import com.plannora.package_service.repository.PackageRepo;
import com.plannora.package_service.service.ItineraryService;
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
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepo itineraryRepo;
    private final PackageRepo packageRepo;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse addItinerary(Long packageId, ItineraryRequestDTO dto) {

        Optional<TravelPackage> pkg = packageRepo.findById(packageId);

        if (pkg.isEmpty()) {
            return error(
                    HttpStatus.NOT_FOUND,
                    ErrorCode.NO_DATA_FOUND,
                    Errors.ERROR_TYPE.PACKAGE
            );
        }

        if (itineraryRepo.findByTravelPackageIdAndDayNo(
                packageId, dto.getDayNo()).isPresent()) {

            return error(
                    HttpStatus.CONFLICT,
                    ErrorCode.ALREADY_EXISTS,
                    Errors.ERROR_TYPE.ITINERARY
            );
        }

        Itinerary itinerary =
                modelMapper.map(dto, Itinerary.class);
        itinerary.setTravelPackage(pkg.get());

        itineraryRepo.save(itinerary);

        return GsUtils.getBaseResponse(
                HttpStatus.CREATED,
                "Itinerary added successfully"
        );
    }

    @Override
    public BaseResponse getByPackageId(Long packageId) {

        List<ItineraryResponseDTO> list =
                itineraryRepo.findByTravelPackageIdOrderByDayNoAsc(packageId)
                        .stream()
                        .map(i -> modelMapper.map(i, ItineraryResponseDTO.class))
                        .toList();

        return GsUtils.getBaseResponse(HttpStatus.OK, list);
    }
}
