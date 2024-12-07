package ru.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
