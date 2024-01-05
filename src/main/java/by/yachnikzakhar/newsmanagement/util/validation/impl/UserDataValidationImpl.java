package by.yachnikzakhar.newsmanagement.util.validation.impl;

import by.yachnikzakhar.newsmanagement.util.validation.UserDataValidation;

public class UserDataValidationImpl implements UserDataValidation {
    @Override
    public boolean checkRegistrationData(String login, String password, String fullName, String phoneNumber) {
        RegistrationValidator validator = RegistrationValidator.getValidator();
        return validator.isValidLogin(login) && validator.isValidPassword(password) && validator.isValidFullName(fullName) && validator.isValidPhoneNumber(phoneNumber);
    }
}
