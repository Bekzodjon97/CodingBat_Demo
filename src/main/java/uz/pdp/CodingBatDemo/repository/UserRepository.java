package uz.pdp.CodingBatDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.CodingBatDemo.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
