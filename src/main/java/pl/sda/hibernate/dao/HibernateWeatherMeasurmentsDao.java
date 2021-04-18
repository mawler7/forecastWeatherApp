package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.WeatherMeasurements;

import java.util.List;

public class HibernateWeatherMeasurmentsDao implements WeatherMeasurmentsDao{
    private final SessionFactory sessionFactory;

    public HibernateWeatherMeasurmentsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public WeatherMeasurements create(WeatherMeasurements weatherMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(weatherMeasurements);

            tx.commit();
            return weatherMeasurements;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public WeatherMeasurements update(WeatherMeasurements weatherMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final WeatherMeasurements updatedMeasurments = (WeatherMeasurements) session.merge(weatherMeasurements);

            tx.commit();
            return updatedMeasurments;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public WeatherMeasurements findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(WeatherMeasurements.class, id);
        }
    }

    @Override
    public void delete(WeatherMeasurements weatherMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(weatherMeasurements);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<WeatherMeasurements> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<WeatherMeasurements> weatherMeasurementsQuery = session.createQuery("from WeatherMeasurements", WeatherMeasurements.class);
            return weatherMeasurementsQuery.getResultList();
        }
    }
    
}



