package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
