package org.example.attendancemanagementsystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.attendancemanagementsystem.model.enums.UserRole;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String fullName;
    private String userName;
    private String phoneNumber;
    private UserRole roles;
    private String email;
    private boolean isVerify;

}
