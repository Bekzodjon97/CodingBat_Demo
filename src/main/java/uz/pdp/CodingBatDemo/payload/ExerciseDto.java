package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
    @NotNull(message = "Chapter nomi bo'sh bo'lishi mumkin emas")
    private String name;

    @NotNull(message = "Savol matni bo'sh bo'lishi mumkin emas")
    private String question;

    @NotNull(message = "Method bo'sh bo'lishi mumkin emas")
    private String method;

    private boolean hasStar;

    private Integer hintSolutionId;

    @NotNull(message = "Chapter bo'sh bo'lishi mumkin emas")
    private Integer chapterid;



}
