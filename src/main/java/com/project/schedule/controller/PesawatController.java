package com.project.schedule.controller;

import com.project.schedule.dto.PesawatDto;
import com.project.schedule.dto.request.PesawatPostRequest;
import com.project.schedule.dto.request.PesawatUpdateRequest;
import com.project.schedule.dto.response.ApiResponse;
import com.project.schedule.dto.response.ListApiResponse;
import com.project.schedule.dto.response.Metadata;
import com.project.schedule.dto.response.PesawatPostResponse;
import com.project.schedule.service.PesawatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.schedule.constant.GeneralConstant;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PesawatController {

    private final PesawatService pesawatService;

    @PostMapping(
            value = "/plane",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<PesawatPostResponse>> createPlane(@RequestBody PesawatPostRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<PesawatPostResponse>builder()
                        .status(HttpStatus.OK)
                        .metadata(null)
                        .message(GeneralConstant.GeneralMessageSuccessApi.SUCCESS_CREATE_DATA)
                        .data(pesawatService.createPesawat(request)).build());
    }

    @GetMapping(
            value = "/planes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ListApiResponse<PesawatDto, Metadata>> getListPlanes(@RequestParam(defaultValue = "1") int page,
                                                                               @RequestParam(defaultValue = "10") int size,
                                                                               @RequestParam(defaultValue = "") String search){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pesawatService.getListPesawat(page -1, size, search));
    }

    @GetMapping(
            value = "/plane/{planeId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<PesawatDto>> getPlane(@PathVariable(value = "planeId") String pesawatId){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<PesawatDto>builder()
                        .metadata(null)
                        .data(pesawatService.getPesawat(pesawatId))
                        .message(GeneralConstant.GeneralMessageSuccessApi.SUCCESS_GET_DATA)
                        .status(HttpStatus.OK).build());
    }

    @PatchMapping(
            value = "/plane/{planeId}"
    )
    public ResponseEntity<ApiResponse<PesawatDto>> updatePlane(@PathVariable(value = "planeId") String pesawatId,
                                                               @RequestBody PesawatUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<PesawatDto>builder()
                        .data(pesawatService.updatePesawat(pesawatId, request))
                        .status(HttpStatus.OK)
                        .metadata(null)
                        .message(GeneralConstant.GeneralMessageSuccessApi.SUCCESS_UPDATE_DATA).build());
    }

    @DeleteMapping(
            value = "/plane/{planeId}"
    )
    public ResponseEntity<ApiResponse<String>> deletePlane(@PathVariable(value = "planeId") String pesawatId){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .message(pesawatService.delete(pesawatId))
                .data(null)
                .metadata(null).build());
    }




}
