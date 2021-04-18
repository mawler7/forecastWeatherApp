package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Location;
import pl.sda.hibernate.entity.WeatherMeasurements;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateWeatherMeasurmentsDaoTest {

    public final WeatherMeasurements testWeatherMeasurements1 = new WeatherMeasurements(
            25,
            1000,
            25,
            10,
            "NW"
    );
    public final WeatherMeasurements testWeatherMeasurements2 = new WeatherMeasurements(
            -10,
            1025,
            50,
            20,
            "SE"
    );

    private HibernateWeatherMeasurmentsDao hibernateWeatherMeasurmentsDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(WeatherMeasurements.class)
                .buildSessionFactory();
        hibernateWeatherMeasurmentsDao = new HibernateWeatherMeasurmentsDao(sessionFactory);
        hibernateWeatherMeasurmentsDao.create(testWeatherMeasurements1);
        hibernateWeatherMeasurmentsDao.create(testWeatherMeasurements2);
    }

    @Test
    void shouldCreateMeasurements() {
        WeatherMeasurements testWeatherMeasurements = new WeatherMeasurements();
        testWeatherMeasurements.setTemperature(25);
        testWeatherMeasurements.setPressure(1005);
        testWeatherMeasurements.setHumidity(50);
        testWeatherMeasurements.setWindSpeed(10);
        testWeatherMeasurements.setWindDirection("WE");


        final int expectedSize = hibernateWeatherMeasurmentsDao.getAll().size() + 1;

        final WeatherMeasurements savedWeatherMeasurements = hibernateWeatherMeasurmentsDao.create(testWeatherMeasurements);

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurmentsDao.findById(savedWeatherMeasurements.getId());
        final int actualSize = hibernateWeatherMeasurmentsDao.getAll().size();

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

        final WeatherMeasurements modifiedWeatherMeasurements = hibernateWeatherMeasurmentsDao.findById(testWeatherMeasurements2.getId());
        modifiedWeatherMeasurements.setTemperature(15);
        modifiedWeatherMeasurements.setPressure(30);
        modifiedWeatherMeasurements.setHumidity(45);
        modifiedWeatherMeasurements.setWindSpeed(20);
        modifiedWeatherMeasurements.setWindDirection("SE");

        final WeatherMeasurements updatedWeatherMeasurements = hibernateWeatherMeasurmentsDao.update(modifiedWeatherMeasurements);

        assertEquals(modifiedWeatherMeasurements, updatedWeatherMeasurements);
        assertNotSame(modifiedWeatherMeasurements, updatedWeatherMeasurements);

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurmentsDao.findById(updatedWeatherMeasurements.getId());
        assertEquals(modifiedWeatherMeasurements, actualWeatherMeasurements);



    }

    @Test
    void findById() {

        final WeatherMeasurements actualWeatherMeasurements = hibernateWeatherMeasurmentsDao.findById(testWeatherMeasurements1.getId());

        assertEquals(testWeatherMeasurements1, actualWeatherMeasurements);

    }

    @Test
    void delete() {

        final int expectedSize = hibernateWeatherMeasurmentsDao.getAll().size() - 1;

        hibernateWeatherMeasurmentsDao.delete(testWeatherMeasurements1);

        final List<WeatherMeasurements> WeatherMeasurementsList = hibernateWeatherMeasurmentsDao.getAll();
        final int actualSize = WeatherMeasurementsList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(WeatherMeasurementsList.contains(testWeatherMeasurements1));

        final WeatherMeasurements unexpectedLocation = hibernateWeatherMeasurmentsDao.findById(testWeatherMeasurements1.getId());
        assertNull(unexpectedLocation);

    }

    @Test
    void getAll() {
        final List<WeatherMeasurements> WeatherMeasurementsList = hibernateWeatherMeasurmentsDao.getAll();

        assertEquals(2, WeatherMeasurementsList.size());
        assertTrue(WeatherMeasurementsList.contains(testWeatherMeasurements1));
        assertTrue(WeatherMeasurementsList.contains(testWeatherMeasurements2));

    }
}