package ru.itis.site.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.site.models.Account;
import ru.itis.site.repositories.AccountsRepository;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // этот метод проверит верные ли логин и пароль
    @Override
    public boolean isCorrectCredentials(String email, String password) {
        Optional<Account> accountOptional = accountsRepository.findByEmail(email);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            // здесь сравним пароли, который ввёл юзер на форме и тот, который мы достали из БД
            if (passwordEncoder.matches(password, account.getHashPassword())) {
                System.out.println(" ! Passwords matches ! ");
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
