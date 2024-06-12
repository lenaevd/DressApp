package Dress.app.repos;

import Dress.app.Models.Look;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LookRepository extends JpaRepository<Look, UUID> {

}





