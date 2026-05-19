package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            String sql = """
                CREATE TABLE IF NOT EXISTS testwork.users (
                  id BIGINT NOT NULL AUTO_INCREMENT,
                  name VARCHAR(255) NOT NULL,
                  lastname VARCHAR(255) NOT NULL,
                  age TINYINT NULL,
                  PRIMARY KEY (id))
                """;

            session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS testwork.users";

            session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.persist(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            users = session.createQuery("FROM User", User.class).getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            session.createMutationQuery("DELETE FROM User").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }
}
