package uz.pdp.CodingBatDemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.CodingBatDemo.entity.Language;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto {

    @NotNull(message = "Chapter nomi bo'sh bo'lishi mumkin emas")
    private String name;

    private String description;

    @NotNull(message = "Til bo'sh bo'lishi mumkin emas")
    private Integer languageId;

}
