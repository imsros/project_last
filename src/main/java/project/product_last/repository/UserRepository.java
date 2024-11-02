package project.product_last.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product_last.model.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
