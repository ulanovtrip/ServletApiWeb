package ru.itis.site.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// это входящий объект
// эти данных приходят со страницы signUp.jsp
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
