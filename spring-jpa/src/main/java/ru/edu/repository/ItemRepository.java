package ru.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.edu.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


    @Query("select i from Item i where i.name = ?1")
    List<Item> findByName(String name);



}
