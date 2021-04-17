package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateApp {

    static SessionFactory sessionFactory;

    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");
    }

    private static void template() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // body here

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }
}
