package org.example.attendancemanagementsystem.dto.dailyAttendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.attendancemanagementsystem.model.enums.AttendanceStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyAttendanceResponseDTO {
    private UUID id;
    private AttendanceStatus status;
    private LocalDate date;
}
