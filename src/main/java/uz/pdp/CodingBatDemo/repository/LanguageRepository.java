package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
}
