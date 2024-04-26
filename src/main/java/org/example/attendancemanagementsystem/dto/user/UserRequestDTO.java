package org.example.attendancemanagementsystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String userName;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
}
