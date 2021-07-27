package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    boolean existsByNameAndLanguageId(String name, Integer language_id);
}
