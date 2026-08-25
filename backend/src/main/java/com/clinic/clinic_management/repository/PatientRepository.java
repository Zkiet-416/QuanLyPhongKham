package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
