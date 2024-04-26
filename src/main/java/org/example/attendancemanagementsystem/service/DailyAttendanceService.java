package org.example.attendancemanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.dto.dailyAttendance.DailyAttendanceRequestDTO;
import org.example.attendancemanagementsystem.dto.dailyAttendance.DailyAttendanceResponseDTO;
import org.example.attendancemanagementsystem.model.AttendanceEntity;
import org.example.attendancemanagementsystem.model.DailyAttendanceEntity;
import org.example.attendancemanagementsystem.repository.DailyAttendanceRepository;
import org.example.attendancemanagementsystem.validation.DailyAttendanceValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyAttendanceService {
    private final DailyAttendanceRepository dailyAttendanceRepository;
    private final DailyAttendanceValidation dailyAttendanceValidation;
    private final AttendanceService attendanceService;
    private final ModelMapper modelMapper;

    public void createDailyAttendanceAndAddToTheList(DailyAttendanceRequestDTO dailyAttendanceRequestDTO, UUID userId) {
        if (!check(dailyAttendanceRequestDTO, userId)) {
            throw new RuntimeException("Invalid request");
        }
        DailyAttendanceEntity dailyAttendance = convertDTOToEntity(dailyAttendanceRequestDTO);
        attendanceService.addDailyAttendanceToList(dailyAttendance, userId);
        dailyAttendanceRepository.save(dailyAttendance);
    }

    public List<DailyAttendanceResponseDTO> getMonthlyAttendance(UUID userId) {
        AttendanceEntity attendance = attendanceService.findByUserId(userId);
        if (attendance.getDailyAttendances().size() <= 30) {
            return attendance.getDailyAttendances().stream().map(this::convertEntityToDTO).toList();
        }
        List<DailyAttendanceEntity> dailyAttendances = attendance.getDailyAttendances();
        List<DailyAttendanceResponseDTO> dailyAttendanceResponse = new ArrayList<>();
        int startIndex = dailyAttendances.size() - 30 - 1;
        for (int i = startIndex; i < dailyAttendances.size(); i++) {
            dailyAttendanceResponse.add(convertEntityToDTO(dailyAttendances.get(i)));
        }
        return dailyAttendanceResponse;
    }

    public void update(UUID dailyAttendanceId, DailyAttendanceRequestDTO dailyAttendanceRequestDTO) {
        DailyAttendanceEntity dailyAttendance = dailyAttendanceRepository.findById(dailyAttendanceId).orElseThrow();
        modelMapper.map(dailyAttendance, dailyAttendanceRequestDTO);
        dailyAttendanceRepository.save(dailyAttendance);
        convertEntityToDTO(dailyAttendance);
    }

    public void delete(UUID dailyAttendanceId) {
        dailyAttendanceRepository.deleteById(dailyAttendanceId);

    }


    private boolean check(DailyAttendanceRequestDTO dailyAttendanceRequestDTO, UUID userId) {
        return dailyAttendanceValidation.checkUserLastDate(dailyAttendanceRequestDTO.getDate(), userId);
    }

    private DailyAttendanceEntity convertDTOToEntity
            (DailyAttendanceRequestDTO dailyAttendanceRequestDTO) {
        return modelMapper.map(dailyAttendanceRequestDTO, DailyAttendanceEntity.class);
    }

    public DailyAttendanceResponseDTO convertEntityToDTO(DailyAttendanceEntity dailyAttendanceEntity) {
        return modelMapper.map(dailyAttendanceEntity, DailyAttendanceResponseDTO.class);
    }
}



