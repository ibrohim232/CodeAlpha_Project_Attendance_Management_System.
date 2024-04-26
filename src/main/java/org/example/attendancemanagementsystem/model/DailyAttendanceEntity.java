package org.example.attendancemanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.attendancemanagementsystem.model.enums.AttendanceStatus;

import java.time.LocalDate;

@Entity(name = "daily-attendances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyAttendanceEntity extends BaseEntity {
    @Enumerated
    @Column(nullable = false)
    private AttendanceStatus status;
    @Column(nullable = false)
    private LocalDate date;

}
