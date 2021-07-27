package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Email bo'sh bo'lishi mumkin emas")
    private String email;

    @NotNull(message = "Password bo'sh bo'lishi mumkin emas")
    private String password;
}
