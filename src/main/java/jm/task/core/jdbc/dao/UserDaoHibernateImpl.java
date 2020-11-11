package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("CREATE TABLE users1 (" +
                    "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(40), " +
                    "lastName VARCHAR(40), " +
                    "age TINYINT UNSIGNED)")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("DROP TABLE users1")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        } finally {
            session.close();
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных ");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> usersList = null;
        try {
            usersList = session.createQuery("FROM User").list();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
