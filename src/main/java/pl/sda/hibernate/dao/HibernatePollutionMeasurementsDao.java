package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.PollutionMeasurements;


import java.util.List;

public class HibernatePollutionMeasurementsDao implements PollutionMeasurementsDao {

    private  final SessionFactory sessionFactory;

    public HibernatePollutionMeasurementsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PollutionMeasurements create(PollutionMeasurements pollutionMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(pollutionMeasurements);

            tx.commit();
            return pollutionMeasurements;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }
    @Override
    public PollutionMeasurements update(PollutionMeasurements pollutionMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final PollutionMeasurements updatePollutionMeasurements = (PollutionMeasurements) session.merge(pollutionMeasurements);

            tx.commit();
            return updatePollutionMeasurements;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }


    @Override
    public PollutionMeasurements findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(PollutionMeasurements.class, id);
        }
    }

    @Override
    public void delete(PollutionMeasurements pollutionMeasurements) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(pollutionMeasurements);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<PollutionMeasurements> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<PollutionMeasurements> pollutionMeasurementsQuery = session.createQuery("from PollutionMeasurements", PollutionMeasurements.class);
            return pollutionMeasurementsQuery.getResultList();
        }
    }

}