package ru.itis.site.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// модели ходят между репозиториями и сервисами, т.е. взаимодействие происходит на уровне моделей

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private String email;
    private String hashPassword;
}
