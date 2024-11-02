package project.product_last.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product_last.model.entities.Color;

public interface ColorRepository extends JpaRepository<Color,Integer>{
}
