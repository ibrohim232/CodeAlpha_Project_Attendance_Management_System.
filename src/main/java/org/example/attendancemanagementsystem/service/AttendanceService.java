package org.example.attendancemanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.model.AttendanceEntity;
import org.example.attendancemanagementsystem.model.DailyAttendanceEntity;
import org.example.attendancemanagementsystem.model.UserEntity;
import org.example.attendancemanagementsystem.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public void addDailyAttendanceToList(DailyAttendanceEntity dailyAttendance, UUID userId){
        AttendanceEntity attendance = attendanceRepository.findByUserId(userId).orElseThrow();
        attendance.getDailyAttendances().add(dailyAttendance);
        attendanceRepository.save(attendance);
    }
    public void createAttendance(UserEntity user){
        AttendanceEntity attendance = new AttendanceEntity(user, new ArrayList<>());
        attendanceRepository.save(attendance);
    }
    public AttendanceEntity findByUserId(UUID userId){
        return attendanceRepository.findByUserId(userId).orElseThrow();
    }


}
