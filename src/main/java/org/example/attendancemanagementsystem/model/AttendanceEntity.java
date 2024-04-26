package org.example.attendancemanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "attendances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceEntity extends BaseEntity {
    @OneToOne
    private UserEntity user;
    @OneToMany
    private List<DailyAttendanceEntity> dailyAttendances;

}
