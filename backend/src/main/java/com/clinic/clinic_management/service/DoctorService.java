package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.Doctor;
import com.clinic.clinic_management.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Lấy tất cả bác sĩ
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Lấy bác sĩ theo ID
    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorRepository.findById(id);
    }
    
    // Thêm bác sĩ mới
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Cập nhật thông tin bác sĩ
    public Doctor updateDoctor(Integer id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ có ID: " + id));
        existingDoctor .setFullName(doctor.getFullName());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setSpecialtyId(doctor.getSpecialtyId());

        return doctorRepository.save(existingDoctor);
    }

    // Xóa bác sĩ theo ID
    public void deleteDoctor(Integer id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bác sĩ có ID: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
