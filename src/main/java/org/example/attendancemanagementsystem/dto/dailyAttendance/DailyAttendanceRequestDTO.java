package org.example.attendancemanagementsystem.dto.dailyAttendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.attendancemanagementsystem.model.enums.AttendanceStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyAttendanceRequestDTO {
    private AttendanceStatus status;
    private LocalDate date;
}
