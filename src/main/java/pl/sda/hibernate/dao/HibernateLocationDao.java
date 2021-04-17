package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.Location;

import java.util.List;

public class HibernateLocationDao implements LocationDao {
    private final SessionFactory sessionFactory;

    public HibernateLocationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location create(Location location) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(location);

            tx.commit();
            return location;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Location update(Location location) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final Location updatedLocation = (Location) session.merge(location);

            tx.commit();
            return updatedLocation;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Location findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Location.class, id);
        }
    }

    @Override
    public void delete(Location location) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(location);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<Location> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Location> locationQuery = session.createQuery("from Location", Location.class);
            return locationQuery.getResultList();
        }
    }
}
