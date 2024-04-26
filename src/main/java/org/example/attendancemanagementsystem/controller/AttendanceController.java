package org.example.attendancemanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.dto.dailyAttendance.DailyAttendanceRequestDTO;
import org.example.attendancemanagementsystem.dto.dailyAttendance.DailyAttendanceResponseDTO;
import org.example.attendancemanagementsystem.service.AttendanceService;
import org.example.attendancemanagementsystem.service.DailyAttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    private final DailyAttendanceService dailyAttendanceService;
    private final AttendanceService attendanceService;

    @PostMapping("/add")
    public String addAttendance(@RequestParam("attendance") DailyAttendanceRequestDTO attendance, Model model) {
        dailyAttendanceService.createDailyAttendanceAndAddToTheList(attendance, UUID.fromString(Objects.requireNonNull(model.getAttribute("userId")).toString()));
        return "menu";
    }

    @GetMapping("/get-monthly-attendace")
    public String getMonthlyAttendance(Model model) {
        UUID userId = UUID.fromString(Objects.requireNonNull(model.getAttribute("userId")).toString());
        List<DailyAttendanceResponseDTO> attendances = dailyAttendanceService.getMonthlyAttendance(userId);
        model.addAttribute("attendances", attendances);
        return "menu";
    }

    @PutMapping("/update-daily-attendance")
    public String updateDailyAttendance(@RequestParam("dailyAttendanceId") UUID id,
                                        @RequestParam("attendance") DailyAttendanceRequestDTO attendance) {
        dailyAttendanceService.update(id, attendance);
        return "menu";
    }
     @DeleteMapping("/delete")
     public String delete(@RequestParam("dailyAttendanceId") UUID id){
        dailyAttendanceService.delete(id);
        return "menu";
     }


}
