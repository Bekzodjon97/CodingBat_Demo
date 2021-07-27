package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Example;

public interface ExampleRepository extends JpaRepository<Example, Integer> {
    boolean existsByText(String text);
}
