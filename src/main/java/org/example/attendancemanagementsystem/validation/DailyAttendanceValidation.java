package org.example.attendancemanagementsystem.validation;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.model.AttendanceEntity;
import org.example.attendancemanagementsystem.model.DailyAttendanceEntity;
import org.example.attendancemanagementsystem.repository.AttendanceRepository;
import org.example.attendancemanagementsystem.repository.DailyAttendanceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DailyAttendanceValidation {
    private final DailyAttendanceRepository dailyAttendanceRepository;
    private final AttendanceRepository attendanceRepository;

    public boolean checkUserLastDate(LocalDate date, UUID userId) {
        AttendanceEntity attendance = attendanceRepository.findByUserId(userId).orElseThrow();
        if (attendance.getDailyAttendances().isEmpty()) {
            return true;
        }
        DailyAttendanceEntity lastAdded = attendance.getDailyAttendances().get(attendance.getDailyAttendances().size() - 1);
        if (lastAdded.getDate().isAfter(date) || lastAdded.getDate().isEqual(date)) {
            return false;
        }
        return true;
    }
}
