package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.Patient;
import com.clinic.clinic_management.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Lấy tất cả bệnh nhân
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Lấy tất cả bệnh nhân theo ID
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    // Thêm bệnh nhân mới
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Cập nhật thông tin bệnh nhân
    public Patient updatePatient(Integer id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân có ID: " + id));
        existingPatient.setFullName(patient.getFullName());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setGender(patient.getGender());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setAddress(patient.getAddress());

        return patientRepository.save(existingPatient);
    }

    // Xóa bệnh nhân theo ID
    public void deletePatient(Integer id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bệnh nhân có ID: " + id);
        }
        patientRepository.deleteById(id);
    }
}
