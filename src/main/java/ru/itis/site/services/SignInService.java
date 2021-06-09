package ru.itis.site.services;

public interface SignInService {

    boolean isCorrectCredentials(String email, String password);
}
