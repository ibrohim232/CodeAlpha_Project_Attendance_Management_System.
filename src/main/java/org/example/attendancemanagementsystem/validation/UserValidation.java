package org.example.attendancemanagementsystem.validation;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.model.UserEntity;
import org.example.attendancemanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.example.attendancemanagementsystem.validation.Pattern.*;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public boolean isValidUserName(String userName) {
        return userRepository.findByUserName(userName).isEmpty();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        Optional<UserEntity> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }

    public boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);

    }

    public boolean isValidEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return false;
        }
        return Pattern.matches(EMAIL_PATTERN, email);
    }
}
