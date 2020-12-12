package ru.kiselev.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.kiselev.hibernate.entities.Car;
import ru.kiselev.hibernate.entities.Country;
import ru.kiselev.hibernate.entities.Passport;
import ru.kiselev.hibernate.entities.Person;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static ru.kiselev.hibernate.utils.HibernateSessionFactory.getSession;

/**
 * @author Oleg Kiselev
 */
public class Main {

    public static void getOne() {
        try (Session session = getSession()) {
            Person person = session.get(Person.class, 7L);
            System.out.println(person);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getList() {
        try (Session session = getSession()) {
            List<Person> list = session.createQuery("FROM Person p ORDER BY p.firstName, p.lastName").getResultList();
            list.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getListSecond() {
        try (Session session = getSession()) {
            List<Country> list = session.createQuery("FROM Country").list();
            list.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void persistOne() {
        try (Session session = getSession()) {
//            Transaction transaction = session.getTransaction();
//            transaction.begin();
            session.beginTransaction();

            Person person = new Person();
            Passport passport = new Passport(person, 159357);
            Country country = new Country("Finland", null);
            person.setFirstName("Boris");
            person.setLastName("Borisov");
            person.setAge(27);
            person.setPassport(passport);
            person.setCountry(country);
            person.setTestTime(ZonedDateTime.now());

            session.persist(person);

//            transaction.commit();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveOne() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person savePerson = new Person();
            savePerson.setId(100L);
            savePerson.setFirstName("Mikhail");
            savePerson.setLastName("Sidorov");
            session.save(savePerson);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveOrUpdateOne() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person saveOrUpdatePerson = new Person();
            saveOrUpdatePerson.setId(48L);
            saveOrUpdatePerson.setFirstName("Mikha");
            saveOrUpdatePerson.setLastName("Sidor");
            session.saveOrUpdate(saveOrUpdatePerson);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateOne() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person updatePerson = session.get(Person.class, 2L);
            updatePerson.setTestTime(ZonedDateTime.now());
            session.update(updatePerson);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void autoUpdateAfterSet() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person updatePerson = session.get(Person.class, 1L);
            System.out.println(updatePerson);
            updatePerson.setFirstName("Roman");

            transaction.commit();

            Person update = session.get(Person.class, 1L);
            System.out.println(update);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void refreshOne() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person refreshPerson = session.get(Person.class,52L);
            System.out.println(refreshPerson);
            refreshPerson.setFirstName("Igorrr");
            session.update(refreshPerson);
            System.out.println(refreshPerson);

            transaction.commit();

            session.refresh(refreshPerson);
            System.out.println(refreshPerson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteOne() {
        try (Session session = getSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person deletePerson = session.get(Person.class, 2L);
            if (deletePerson != null)
                session.delete(deletePerson);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getList();

//        getListSecond();

//        getOne();

//        refreshOne();

//        saveOrUpdateOne();

//        saveOne();

//        deleteOne();

//        updateOne();

//        autoUpdateAfterSet();

//        persistOne();

//        getSession();
    }

}
