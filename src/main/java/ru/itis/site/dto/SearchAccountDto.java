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
public class SearchAccountDto {
    private String firstName;
    private String lastName;

    // конвертация модели
    public static SearchAccountDto from(Account account) {
        return SearchAccountDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }

    // конвертация списка аккаунтов
    public static List<SearchAccountDto> from(List<Account> accounts) {
        return accounts.stream().map(SearchAccountDto::from).collect(Collectors.toList());
    }
}
