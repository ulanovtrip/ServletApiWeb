package ru.itis.site.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.site.models.Account;

import java.util.*;

@Repository
public class AccountsRepositoryJdbcTemplateImpl implements AccountsRepository {

    //language=SQL
    private static final String SQL_SEARCH = "select * from account where first_name ilike (:query) or last_name ilike (:query)";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into account(first_name, last_name, email, hash_password) " +
            "values (:firstName, :lastName, :email, :hashPassword)";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "select * from account where email = :email";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountsRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // так строка из БД должна мапиться в объект Account
    private RowMapper<Account> accountRowMapper = (row, rowNumber) ->
            Account.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .experience(row.getInt("experience"))
                    .email(row.getString("email"))
                    .hashPassword(row.getString("hash_password"))
                    .build();

    @Override
    public List<Account> findByFirstNameOrLastNameContains(String name) {

        Map<String, Object> params = Collections.singletonMap("query", "%" + name + "%");

        return namedParameterJdbcTemplate.query(SQL_SEARCH, params, accountRowMapper);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        try {
            // ищем в БД юзера
            Account account = namedParameterJdbcTemplate.queryForObject(
                    SQL_FIND_BY_EMAIL,
                    Collections.singletonMap("email", email),
                    accountRowMapper
            );
            return Optional.of(account);
        } catch (EmptyResultDataAccessException e) {
            //  если в бд никого не нашли с таким email
            return Optional.empty();
        }
    }

    @Override
    public List<Account> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, accountRowMapper);
    }

    @Override
    public void save(Account entity) {
        // создадим params
        Map<String, Object> params = new HashMap<>();
        // это то, что нужно подставить при сохранении в account(first_name, last_name, email, hash_password)
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("email", entity.getEmail());
        params.put("hashPassword", entity.getHashPassword());

        // выполняем запрос
        namedParameterJdbcTemplate.update(SQL_INSERT_USER, params);
    }
}
