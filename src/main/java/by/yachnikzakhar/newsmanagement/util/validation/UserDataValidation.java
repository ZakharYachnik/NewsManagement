package by.yachnikzakhar.newsmanagement.util.validation;

public interface UserDataValidation {
    boolean checkRegistrationData(String login, String password, String fullName, String phoneNumber);
}
