package org.example.attendancemanagementsystem.repository;

import org.example.attendancemanagementsystem.model.DailyAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DailyAttendanceRepository extends JpaRepository<DailyAttendanceEntity, UUID> {
}
