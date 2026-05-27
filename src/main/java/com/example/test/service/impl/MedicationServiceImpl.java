package com.example.test.service.impl;

import com.example.test.exception.ResourceNotFoundException;
import com.example.test.model.dto.request.MedicationRequest;
import com.example.test.model.dto.response.MedicationResponse;
import com.example.test.model.entity.Medication;
import com.example.test.model.entity.Status;
import com.example.test.repository.IMedicationRepository;
import com.example.test.service.IMedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements IMedicationService {

    private final IMedicationRepository medicationRepository;

    // Convert Entity -> DTO
    private MedicationResponse mapToResponse(Medication medication) {
        return new MedicationResponse(
                medication.getId(),
                medication.getName(),
                medication.getManufacturer(),
                medication.getPrice(),
                medication.getStatus(),
                medication.getIsDeleted()
        );
    }

    // Lấy tất cả thuốc
    @Override
    public List<MedicationResponse> getAllMedications() {

        // Lấy toàn bộ dữ liệu từ database
        List<Medication> medications = medicationRepository.findAll();

        // Convert List<Entity> -> List<ResponseDTO>
        return medications.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Lấy theo id
    @Override
    public MedicationResponse getMedicationById(Long id) {

        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy thuốc")
                );

        return mapToResponse(medication);
    }

    // Create
    @Override
    public MedicationResponse saveMedication(MedicationRequest medication) {

        Medication newMedication = new Medication();

        newMedication.setName(medication.getName());
        newMedication.setManufacturer(medication.getManufacturer());
        newMedication.setPrice(medication.getPrice());
        newMedication.setStatus(Status.AVAILABLE);
        newMedication.setIsDeleted(false);

        medicationRepository.save(newMedication);

        return mapToResponse(newMedication);
    }
    // PUT
    @Override
    public MedicationResponse putMedication(Long id, MedicationRequest medication) {

        Medication oldMedication = medicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy thuốc")
                );

        oldMedication.setName(medication.getName());
        oldMedication.setManufacturer(medication.getManufacturer());
        oldMedication.setPrice(medication.getPrice());

        medicationRepository.save(oldMedication);

        return mapToResponse(oldMedication);
    }

    // PATCH
    @Override
    public MedicationResponse patchMedication(Long id, MedicationRequest medication) {

        Medication oldMedication = medicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy thuốc")
                );

        // Chỉ update field nào khác null
        if (medication.getName() != null) {
            oldMedication.setName(medication.getName());
        }

        if (medication.getManufacturer() != null) {
            oldMedication.setManufacturer(medication.getManufacturer());
        }

        if (medication.getPrice() != null) {
            oldMedication.setPrice(medication.getPrice());
        }

        medicationRepository.save(oldMedication);

        return mapToResponse(oldMedication);
    }

    // Soft delete
    @Override
    public void deleteMedicationById(Long id) {

        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy thuốc")
                );
        medication.setIsDeleted(true);

        medicationRepository.save(medication);
    }
}