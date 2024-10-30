package Dress.app.repos;

import Dress.app.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findAllByTypeIn(List<String> types);

    Item findByLink(String link);
}
