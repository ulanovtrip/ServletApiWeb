package ru.itis.site.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.site.forms.SignUpForm;
import ru.itis.site.models.Account;
import ru.itis.site.repositories.AccountsRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {
        // создаём объект аккаунт
        Account account = Account.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                // захэшировали пароль, это уже бизнес-логика, это должно быть в сервисе, не в репозитории
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();

        accountsRepository.save(account);
    }
}
