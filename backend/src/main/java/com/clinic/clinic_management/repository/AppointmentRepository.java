package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {   
}
