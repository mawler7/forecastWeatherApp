package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.Services;

import java.util.List;

public class HibernateServicesDao implements ServicesDao {

    private final SessionFactory sessionFactory;

    public HibernateServicesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }




    @Override
    public Services create(Services services) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(services);

            tx.commit();
            return services;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Services update(Services services) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final Services updatedServices = (Services) session.merge(services);

            tx.commit();
            return updatedServices;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Services findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Services.class, id);
        }
    }

    @Override
    public void delete(Services services) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(services);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<Services> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Services> servicesQuery = session.createQuery("from Services", Services.class);
            return servicesQuery.getResultList();
        }
    }

}



