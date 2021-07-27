package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HintSolutionDto {
    @NotNull(message = "HintSolution matni bo'sh bo'lishi mumkin emas")
    private String text;

}
