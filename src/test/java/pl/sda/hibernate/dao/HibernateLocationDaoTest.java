package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class HibernateLocationDaoTest {

    public static final Location TEST_LOCATION_1 = new Location(
            33,
            44,
            "test region",
            "test city",
            "test country"
    );
    public static final Location TEST_LOCATION_2 = new Location(
            -21,
            -43,
            "test region 2",
            "test city 2",
            "test country 2"
    );
    private HibernateLocationDao hibernateLocationDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Location.class)
                .buildSessionFactory();
        hibernateLocationDao = new HibernateLocationDao(sessionFactory);

        hibernateLocationDao.create(TEST_LOCATION_1);
        hibernateLocationDao.create(TEST_LOCATION_2);
    }

    @Test
    void shouldCreateLocation() {
        Location testLocation = new Location();
        testLocation.setLongitude(54);
        testLocation.setLatitude(32);
        testLocation.setCity("Poznań");
        testLocation.setRegion("test region");
        testLocation.setCountry("Polska");
        final int expectedSize = hibernateLocationDao.getAll().size() + 1;

        final Location savedLocation = hibernateLocationDao.create(testLocation);

        final Location actualLocation = hibernateLocationDao.findById(savedLocation.getId());
        final int actualSize = hibernateLocationDao.getAll().size();

        final Location expectedLocation = new Location();
        expectedLocation.setId(savedLocation.getId());
        expectedLocation.setLatitude(testLocation.getLatitude());
        expectedLocation.setLongitude(testLocation.getLongitude());
        expectedLocation.setRegion(testLocation.getRegion());
        expectedLocation.setCountry(testLocation.getCountry());
        expectedLocation.setCity(testLocation.getCity());

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedLocation, actualLocation);
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }
}