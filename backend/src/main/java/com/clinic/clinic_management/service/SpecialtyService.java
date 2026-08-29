package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.Specialty;
import com.clinic.clinic_management.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    // Lấy tất cả chuyên khoa
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    // Lấy chuyên khoa theo ID
    public Optional<Specialty> getSpecialtyById(Integer id) {
        return specialtyRepository.findById(id);
    }

    // Thêm chuyên khoa mới
    public Specialty addSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }


    // Cập nhật thông tin chuyên khoa
    public Specialty updateSpecialty(Integer id, Specialty specialty) {
        Specialty existingSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa có ID: " + id));
        existingSpecialty.setName(specialty.getName());
        existingSpecialty.setDescription(specialty.getDescription());

        return specialtyRepository.save(existingSpecialty);
    }

    // Xóa chuyên khoa theo ID
    public void deleteSpecialty(Integer id) {
        if (!specialtyRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy chuyên khoa có ID: " + id);
        }
        specialtyRepository.deleteById(id);
    }
}
