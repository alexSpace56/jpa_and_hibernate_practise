package hibernate;


import hibernate.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class saveLoadItemTest {

    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Item.class);
        return configuration.buildSessionFactory();

    }

    @Test
    public void saveLoadItem(){

        try(SessionFactory sf = createSessionFactory();
            Session session = sf.openSession()){

            session.beginTransaction();

            Item item = Item.builder()
                    .name("Mouse")
                    .details("Mouse Ryzen")
                    .build();

            session.persist(item);
            item.setName("Mouse R");
            session.persist(item);
            session.getTransaction().commit();

            session.beginTransaction();
            Item itemF = session.find(Item.class, 1L);
            session.getTransaction().commit();

            assertAll(
                    () -> assertEquals("Mouse R", itemF.getName())
            );


        }

    }



}
