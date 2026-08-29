package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.Appointment;
import com.clinic.clinic_management.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Lấy tất cả cuộc hẹn
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Lấy cuộc hẹn theo ID
    public Optional<Appointment> getAppointmentById(Integer id) {   
        return appointmentRepository.findById(id);
    }

    // Thêm cuộc hẹn mới
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Cập nhật thông tin cuộc hẹn
    public Appointment updateAppointment(Integer id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc hẹn có ID: " + id));
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setReason(appointment.getReason());
        existingAppointment.setStatus(appointment.getStatus());
        existingAppointment.setDoctorId(appointment.getDoctorId());
        existingAppointment.setPatientId(appointment.getPatientId());

        return appointmentRepository.save(existingAppointment);
    }

    // Xóa cuộc hẹn theo ID
    public void deleteAppointment(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy cuộc hẹn có ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
