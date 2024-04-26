package org.example.attendancemanagementsystem.repository;

import org.example.attendancemanagementsystem.model.AttendanceEntity;
import org.example.attendancemanagementsystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, UUID> {
    Optional<AttendanceEntity> findByUserId(UUID userId);
}
