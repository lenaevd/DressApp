package Dress.app.repos;

import Dress.app.Models.Look;
import Dress.app.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LookRepository extends JpaRepository<Look, UUID> {
    List<Look> findByUser(User user);
}





