package com.example.test.service;

import com.example.test.model.dto.request.MedicationRequest;
import com.example.test.model.dto.response.MedicationResponse;

import java.util.List;


public interface IMedicationService {

    // Lấy danh sách thuốc
    List<MedicationResponse> getAllMedications();

    // Lấy thuốc theo id
    MedicationResponse getMedicationById(Long id);

    // Thêm thuốc
    MedicationResponse saveMedication(MedicationRequest medication);

    // Update toàn phần
    MedicationResponse putMedication(Long id, MedicationRequest medication);

    // Update một phần
    MedicationResponse patchMedication(Long id, MedicationRequest medication);

    // Xóa thuốc
    void deleteMedicationById(Long id);
}