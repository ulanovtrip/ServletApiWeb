package ru.itis.site.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.site.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// это исходящие объекты
public class AccountDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    // конвертации модели в dto
    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId().toString())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .build();
    }

    // конвертация списка моделей в список dto
    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
