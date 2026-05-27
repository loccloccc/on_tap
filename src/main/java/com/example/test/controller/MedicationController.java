package com.example.test.controller;

import com.example.test.model.dto.request.MedicationRequest;
import com.example.test.model.dto.response.ApiDataResponse;
import com.example.test.model.dto.response.MedicationResponse;
import com.example.test.service.IMedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medications")
public class MedicationController {

    // Inject interface thay vì implementation
    private final IMedicationService medicationService;

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<MedicationResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy danh sách thuốc thành công",
                        medicationService.getAllMedications()
                )
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicationResponse>> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy thuốc thành công",
                        medicationService.getMedicationById(id)
                )
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiDataResponse<MedicationResponse>> create(
            @Valid @RequestBody MedicationRequest medication
    ) {

        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Thêm thuốc thành công",
                        medicationService.saveMedication(medication)
                ),
                HttpStatus.CREATED
        );
    } // PUT
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicationRequest medication
    ) {

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật thành công",
                        medicationService.putMedication(id, medication)
                )
        );
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicationResponse>> patch(
            @PathVariable Long id,
            @RequestBody MedicationRequest medication
    ) {

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật một phần thành công",
                        medicationService.patchMedication(id, medication)
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<?>> delete(
            @PathVariable Long id
    ) {

        medicationService.deleteMedicationById(id);

        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Xóa thành công",
                        null
                )
        );
    }
}