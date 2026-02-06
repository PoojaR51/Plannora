package com.plannora.package_service.service.Impl;

import com.plannora.package_service.dto.request.DestinationRequestDTO;
import com.plannora.package_service.dto.response.DestinationResponseDTO;
import com.plannora.package_service.entity.Destination;
import com.plannora.package_service.repository.DestinationRepo;
import com.plannora.package_service.service.DestinationService;
import com.plannora.package_service.utils.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plannora.package_service.utils.GsUtils.error;

@Service
@RequiredArgsConstructor
@Transactional
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepo destinationRepo;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse create(DestinationRequestDTO dto) {

        if (destinationRepo.existsByCityAndPlaceName(
                dto.getCity(), dto.getPlaceName())) {

            return error(
                    HttpStatus.CONFLICT,
                    ErrorCode.ALREADY_EXISTS,
                    Errors.ERROR_TYPE.DESTINATION
            );
        }

        Destination destination =
                modelMapper.map(dto, Destination.class);

        destinationRepo.save(destination);

        return GsUtils.getBaseResponse(
                HttpStatus.CREATED,
                "Destination created successfully"
        );
    }

    @Override
    public BaseResponse getAll() {

        List<DestinationResponseDTO> list =
                destinationRepo.findAll()
                        .stream()
                        .map(d -> modelMapper.map(d, DestinationResponseDTO.class))
                        .toList();

        return GsUtils.getBaseResponse(HttpStatus.OK, list);
    }
}
