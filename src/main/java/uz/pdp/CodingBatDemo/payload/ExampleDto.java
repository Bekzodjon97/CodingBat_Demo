package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {
    @NotNull(message = "Chapter nomi bo'sh bo'lishi mumkin emas")
    private String text;

    @NotNull(message = "Namuna bo'sh bo'lishi mumkin emas")
    private Integer exerciseId;

}
