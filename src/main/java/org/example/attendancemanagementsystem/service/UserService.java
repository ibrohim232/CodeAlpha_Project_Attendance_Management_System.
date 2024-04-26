package org.example.attendancemanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.dto.user.UserRequestDTO;
import org.example.attendancemanagementsystem.dto.user.UserResponseDTO;
import org.example.attendancemanagementsystem.model.UserEntity;
import org.example.attendancemanagementsystem.model.enums.UserRole;
import org.example.attendancemanagementsystem.repository.UserRepository;
import org.example.attendancemanagementsystem.validation.UserValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.example.attendancemanagementsystem.validation.Pattern.CHARACTERS;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final UserValidation userValidation;
    private final AttendanceService attendanceService;


    public UserResponseDTO crateUser(UserRequestDTO userRequestDTO) {
        checkUserFields(userRequestDTO);
        UserEntity user = convertDTOToEntity(userRequestDTO);
        user.setRoles(UserRole.USER);
        user.setCode(generateValidationCode());
        userRepository.save(user);
        attendanceService.createAttendance(user);
        return convertEntityToDTO(user);
    }


    public UserResponseDTO singInUser(String userName, String password) {
        UserEntity user = checkUserInput(userName, password);
        return convertEntityToDTO(user);
    }

    public void verifyCode(UUID userId, String code) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        if (!user.getCode().equals(code)) {
            throw new RuntimeException();
        }
        user.setVerify(true);
        userRepository.save(user);
    }

    public UserResponseDTO updateUserInformation(UUID userId, UserRequestDTO userRequestDTO) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        modelMapper.map(userRequestDTO, user);
        userRepository.save(user);
        return convertEntityToDTO(user);
    }

    public void deleteUser(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
    }

    private void checkUserFields(UserRequestDTO userRequestDTO) {
        if (!userValidation.isValidEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException();
        }
        if (!userValidation.isValidPassword(userRequestDTO.getPassword())) {
            throw new RuntimeException();
        }
        if (!userValidation.isValidUserName(userRequestDTO.getUserName())) {
            throw new RuntimeException();
        }
        if (!userValidation.isValidPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new RuntimeException();
        }
    }

    private String generateValidationCode() {
        StringBuilder sb = new StringBuilder();
        Random RANDOM = new Random();
        for (int i = 0; i < 6; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private UserEntity checkUserInput(String userName, String password) {
        Optional<UserEntity> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (user.get().isActive()) {
            throw new RuntimeException("User is not active");
        }
        if (!user.get().isVerify()) {
            throw new RuntimeException("User is not verify");
        }
        if (!user.get().getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        return user.get();
    }

    private UserEntity convertDTOToEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, UserEntity.class);
    }

    private UserResponseDTO convertEntityToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
