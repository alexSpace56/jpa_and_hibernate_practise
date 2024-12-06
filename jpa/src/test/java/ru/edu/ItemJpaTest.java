package ru.edu;

import org.junit.jupiter.api.Test;
import ru.edu.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemJpaTest {
    @Test
    public void storeLoadItem() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cds");
        try{
            EntityManager em = emf.createEntityManager();

            Item item = Item.builder()
                    .name("Orange")
                    .details("Orange fruit")
                    .build();

            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();

            em.getTransaction().begin();

            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
            items.forEach(System.out::println);

            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, items.size()),
                    () -> assertEquals("Orange", items.get(0).getName())
            );

            em.close();
        }finally {
            emf.close();
        }

    }


}
