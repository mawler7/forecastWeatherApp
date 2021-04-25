package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.PollutionMeasurements;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HibernatePollutionMeasurementsDaoTest {

    public final PollutionMeasurements testPollutionMeasurements1 = new PollutionMeasurements (
            25.00,
            1000.01,
            25.30,
            10.40,
            111.60,
            67,34,89.65
    );
    public final PollutionMeasurements testPollutionMeasurements2 = new PollutionMeasurements(
            -10.30,
            1025.79,
            50.45,
            20.32,
            114.54,89.90,65.09,76.08
    );

    private HibernatePollutionMeasurementsDao hibernatePollutionMeasurementsDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(PollutionMeasurements.class)
                .buildSessionFactory();
        hibernatePollutionMeasurementsDao = new HibernatePollutionMeasurementsDao(sessionFactory);
        hibernatePollutionMeasurementsDao.create(testPollutionMeasurements1);
        hibernatePollutionMeasurementsDao.create(testPollutionMeasurements2);
    }

    @Test
    void shouldCreatePollutionMeasurements() {
        PollutionMeasurements testPollutionMeasurements= new PollutionMeasurements();
        testPollutionMeasurements.setCarbonMonoxide(25.99);
        testPollutionMeasurements.setNitrogenMonoxide(1005.66);
        testPollutionMeasurements.setNitrogenDioxide(50.55);
        testPollutionMeasurements.setOzone(10.02);
        testPollutionMeasurements.setSulphurDioxide(19.45);
        testPollutionMeasurements.setAmmonia(112.54);
        testPollutionMeasurements.setFineParticulateMatter(12.31);
        testPollutionMeasurements.setCoarseParticulateMatter(11.00);



        final int expectedSize = hibernatePollutionMeasurementsDao.getAll().size() + 1;

        final PollutionMeasurements savedPollutionMeasurements = hibernatePollutionMeasurementsDao.create(testPollutionMeasurements);

        final PollutionMeasurements actualPollutionMeasurements = hibernatePollutionMeasurementsDao.findById(savedPollutionMeasurements.getId());
        final int actualSize = hibernatePollutionMeasurementsDao.getAll().size();

        final PollutionMeasurements expectedPollutionMeasurements = new PollutionMeasurements();
        expectedPollutionMeasurements.setId(savedPollutionMeasurements.getId());
        expectedPollutionMeasurements.setCarbonMonoxide(testPollutionMeasurements.getCarbonMonoxide());
        expectedPollutionMeasurements.setNitrogenMonoxide(testPollutionMeasurements.getNitrogenMonoxide());
        expectedPollutionMeasurements.setNitrogenDioxide(testPollutionMeasurements.getNitrogenDioxide());
        expectedPollutionMeasurements.setOzone(testPollutionMeasurements.getOzone());
        expectedPollutionMeasurements.setSulphurDioxide(testPollutionMeasurements.getSulphurDioxide());

        expectedPollutionMeasurements.setAmmonia(testPollutionMeasurements.getAmmonia());
        expectedPollutionMeasurements.setFineParticulateMatter(testPollutionMeasurements.getFineParticulateMatter());
        expectedPollutionMeasurements.setCoarseParticulateMatter(testPollutionMeasurements.getCoarseParticulateMatter());

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedPollutionMeasurements, actualPollutionMeasurements);


    }

    @Test
    void update() {

        final PollutionMeasurements modifiedPollutionMeasurements = hibernatePollutionMeasurementsDao.findById(testPollutionMeasurements2.getId());
        modifiedPollutionMeasurements.setCarbonMonoxide(15.11);
        modifiedPollutionMeasurements.setNitrogenMonoxide(30.09);
        modifiedPollutionMeasurements.setNitrogenDioxide(45.89);
        modifiedPollutionMeasurements.setOzone(20.07);
        modifiedPollutionMeasurements.setSulphurDioxide(28.07);

        modifiedPollutionMeasurements.setAmmonia(110.34);
        modifiedPollutionMeasurements.setFineParticulateMatter(145.56);
        modifiedPollutionMeasurements.setCoarseParticulateMatter(34.59);


        final PollutionMeasurements updatedPollutionMeasurements = hibernatePollutionMeasurementsDao.update(modifiedPollutionMeasurements);

        assertEquals(modifiedPollutionMeasurements, updatedPollutionMeasurements);
        assertNotSame(modifiedPollutionMeasurements, updatedPollutionMeasurements);

        final PollutionMeasurements actualPollutionMeasurements = hibernatePollutionMeasurementsDao.findById(updatedPollutionMeasurements.getId());
        assertEquals(modifiedPollutionMeasurements,actualPollutionMeasurements);



    }

    @Test
    void findById() {

        final PollutionMeasurements actualPollutionMeasurements = hibernatePollutionMeasurementsDao.findById(testPollutionMeasurements1.getId());

        assertEquals(testPollutionMeasurements1, actualPollutionMeasurements);

    }

    @Test
    void delete() {

        final int expectedSize = hibernatePollutionMeasurementsDao.getAll().size() - 1;

        hibernatePollutionMeasurementsDao.delete(testPollutionMeasurements1);

        final List<PollutionMeasurements> PollutionMeasurementsList = hibernatePollutionMeasurementsDao.getAll();
        final int actualSize = PollutionMeasurementsList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(PollutionMeasurementsList.contains(testPollutionMeasurements1));

        final PollutionMeasurements unexpectedLocation = hibernatePollutionMeasurementsDao.findById(testPollutionMeasurements1.getId());
        assertNull(unexpectedLocation);

    }

    @Test
    void getAll() {
        final List<PollutionMeasurements> PollutionMeasurementsList = hibernatePollutionMeasurementsDao.getAll();

        assertEquals(2,PollutionMeasurementsList.size());
        assertTrue(PollutionMeasurementsList.contains(testPollutionMeasurements1));
        assertTrue(PollutionMeasurementsList.contains(testPollutionMeasurements2));

    }
}


