package ru.itis.site.services;

import ru.itis.site.dto.AccountDto;
import ru.itis.site.dto.SearchAccountDto;

import java.util.List;

public interface AccountsService {

    // по запросу найти все аккаунты
    List<SearchAccountDto> search(String query);

    // вернуть всех пользователей
    List<AccountDto> getAll();
}
