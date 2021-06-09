package ru.itis.site.repositories;

import ru.itis.site.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends CrudRepository<Account, Long> {

    // найти всех юзеров, firstname и lastname включают в себя name
    List<Account> findByFirstNameOrLastNameContains(String name);

    Optional<Account> findByEmail(String email);
}
