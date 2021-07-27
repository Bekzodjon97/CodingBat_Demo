package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.CodingBatDemo.entity.Exercise;
import uz.pdp.CodingBatDemo.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDto {
    @NotNull(message = "Solution matni bo'sh bo'lishi mumkin emas")
    private String text;

    @NotNull(message = "Masala bo'sh bo'lishi mumkin emas")
    private Integer exerciseId;

    @NotNull(message = "Email bo'sh bo'lishi mumkin emas")
    private String userEmail;

}
