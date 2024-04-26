package org.example.attendancemanagementsystem.validation;

public interface Pattern {
    String PHONE_NUMBER_PATTERN = "^\\+998((0-9){2}|[0-9]{2})[0-9]{7}$";
    String PASSWORD_PATTERN = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    String EMAIL_PATTERN = "^[a-zA-Z0-9_! #$%&'*+/=?`{|}~^. -]+@[a-zA-Z0-9. -]+$";
    String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
}
