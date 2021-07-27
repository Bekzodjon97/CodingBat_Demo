package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.HintSolution;

public interface HintSolutionRepository extends JpaRepository<HintSolution, Integer> {
    boolean existsByText(String text);
}
