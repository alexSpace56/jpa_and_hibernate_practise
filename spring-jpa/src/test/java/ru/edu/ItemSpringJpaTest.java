package ru.edu;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.edu.config.JpaConfig;
import ru.edu.entity.Item;
import ru.edu.repository.ItemRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfig.class})
public class ItemSpringJpaTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void loadStoreItem(){

        Item item = Item.builder()
                .name("Book")
                .details("Fantasy")
                .build();

        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Book", items.get(0).getName())
        );

    }


    @Test
    public void testManualQuery(){
        Item item = Item.builder()
                .name("Alex")
                .details("Human").build();

        itemRepository.save(item);

        List<Item> alex = itemRepository.findByName("Alex");

        assertEquals("Alex", alex.get(0).getName());

    }

}
