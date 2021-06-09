package ru.itis.site.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.site.dto.AccountDto;
import ru.itis.site.dto.SearchAccountDto;
import ru.itis.site.repositories.AccountsRepository;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public List<SearchAccountDto> search(String query) {
        return SearchAccountDto.from(accountsRepository.findByFirstNameOrLastNameContains(query));
    }


    @Override
    public List<AccountDto> getAll() {
        return AccountDto.from(accountsRepository.findAll());
    }
}
