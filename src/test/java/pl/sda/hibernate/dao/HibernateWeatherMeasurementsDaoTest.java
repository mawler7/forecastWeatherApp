package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.WeatherMeasurements;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateWeatherMeasurementsDaoTest {

    public final WeatherMeasurements testWeatherMeasurements1 = new WeatherMeasurements(
            25,
            1000,
            25,
            10,
            111
    );
    public final WeatherMeasurements testWeatherMeasurements2 = new WeatherMeasurements(
            -10,
            1025,
            50,
            20,
            114
    );

    private HibernateWeatherMeasurementsDao hibernateWeatherMeasurementsDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(WeatherMeasurements.class)
                .buildSessionFactory();
        hibernateWeatherMeasurementsDao = new HibernateWeatherMeasurementsDao(sessionFactory);
        hibernateWeatherMeasurementsDao.create(testWeatherMeasurements1);
        hibernateWeatherMeasurementsDao.create(testWeatherMeasurements2);
    }

    @Test
    void shouldCreateMeasurements() {
        WeatherMeasurements testWeatherMeasurements = new WeatherMeasurements();
        testWeatherMeasurements.setTemperature(25);
        testWeatherMeasurements.setPressure(1005);
        testWeatherMeasurements.setHumidity(50);
        testWeatherMeasurements.setWindSpeed(10);
        testWeatherMeasurements.setWindDirection(112);


        final int expectedSize = hibernateWeatherMeasurementsDao.getAll().size() + 1;

        final WeatherMeasurements savedWeatherMeasurements = hibernateWeatherMeasurementsDao.create(testWeatherMeasurements);

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurementsDao.findById(savedWeatherMeasurements.getId());
        final int actualSize = hibernateWeatherMeasurementsDao.getAll().size();

        final WeatherMeasurements expectedWeatherMeasurements = new WeatherMeasurements();
        expectedWeatherMeasurements.setId(savedWeatherMeasurements.getId());
        expectedWeatherMeasurements.setTemperature(testWeatherMeasurements.getTemperature());
        expectedWeatherMeasurements.setPressure(testWeatherMeasurements.getPressure());
        expectedWeatherMeasurements.setHumidity(testWeatherMeasurements.getHumidity());
        expectedWeatherMeasurements.setWindSpeed(testWeatherMeasurements.getWindSpeed());
        expectedWeatherMeasurements.setWindDirection(testWeatherMeasurements.getWindDirection());

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedWeatherMeasurements, actualWeatherMeasurements);


    }

    @Test
    void update() {

        final WeatherMeasurements modifiedWeatherMeasurements = hibernateWeatherMeasurementsDao.findById(testWeatherMeasurements2.getId());
        modifiedWeatherMeasurements.setTemperature(15);
        modifiedWeatherMeasurements.setPressure(30);
        modifiedWeatherMeasurements.setHumidity(45);
        modifiedWeatherMeasurements.setWindSpeed(20);
        modifiedWeatherMeasurements.setWindDirection(110);

        final WeatherMeasurements updatedWeatherMeasurements = hibernateWeatherMeasurementsDao.update(modifiedWeatherMeasurements);

        assertEquals(modifiedWeatherMeasurements, updatedWeatherMeasurements);
        assertNotSame(modifiedWeatherMeasurements, updatedWeatherMeasurements);

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurementsDao.findById(updatedWeatherMeasurements.getId());
        assertEquals(modifiedWeatherMeasurements, actualWeatherMeasurements);



    }

    @Test
    void findById() {

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurementsDao.findById(testWeatherMeasurements1.getId());

        assertEquals(testWeatherMeasurements1, actualWeatherMeasurements);

    }

    @Test
    void delete() {

        final int expectedSize = hibernateWeatherMeasurementsDao.getAll().size() - 1;

        hibernateWeatherMeasurementsDao.delete(testWeatherMeasurements1);

        final List<WeatherMeasurements> WeatherMeasurementsList = hibernateWeatherMeasurementsDao.getAll();
        final int actualSize = WeatherMeasurementsList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(WeatherMeasurementsList.contains(testWeatherMeasurements1));

        final WeatherMeasurements unexpectedLocation = hibernateWeatherMeasurementsDao.findById(testWeatherMeasurements1.getId());
        assertNull(unexpectedLocation);

    }

    @Test
    void getAll() {
        final List<WeatherMeasurements> WeatherMeasurementsList = hibernateWeatherMeasurementsDao.getAll();

        assertEquals(2, WeatherMeasurementsList.size());
        assertTrue(WeatherMeasurementsList.contains(testWeatherMeasurements1));
        assertTrue(WeatherMeasurementsList.contains(testWeatherMeasurements2));

    }
}