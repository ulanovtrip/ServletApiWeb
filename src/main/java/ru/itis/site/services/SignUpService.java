package ru.itis.site.services;

import ru.itis.site.forms.SignUpForm;

public interface SignUpService {

    // регистрация нового пользователя
    void signUp(SignUpForm form);
}
