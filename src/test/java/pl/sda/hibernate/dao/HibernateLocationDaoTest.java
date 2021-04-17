package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateLocationDaoTest {

    public final Location testLocation1 = new Location(
            33,
            44,
            "test region",
            "test city",
            "test country"
    );
    public final Location testLocation2 = new Location(
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

        final Location savedTestLocation1 = hibernateLocationDao.create(testLocation1);
        testLocation1.setId(savedTestLocation1.getId());
        final Location savedTestLocation2 = hibernateLocationDao.create(testLocation2);
        testLocation2.setId(savedTestLocation2.getId());
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

    @Test
    void shouldFindById() {

        final Location actualLocation = hibernateLocationDao.findById(testLocation1.getId());

        assertEquals(testLocation1, actualLocation);
    }

    @Test
    void shouldUpdateLocation() {

        final Location modifiedLocation = hibernateLocationDao.findById(testLocation2.getId());
        modifiedLocation.setLatitude(1);
        modifiedLocation.setLongitude(-1);
        modifiedLocation.setCountry("modified country");
        modifiedLocation.setCity("modified city");
        modifiedLocation.setRegion("modified region");

        final Location updatedLocation = hibernateLocationDao.update(modifiedLocation);

        assertEquals(modifiedLocation, updatedLocation);
        assertNotSame(modifiedLocation, updatedLocation);

        final Location actualLocation = hibernateLocationDao.findById(updatedLocation.getId());
        assertEquals(modifiedLocation, actualLocation);
    }

    @Test
    void shouldDeleteLocation() {
        final int expectedSize = hibernateLocationDao.getAll().size() - 1;

        hibernateLocationDao.delete(testLocation1);

        final List<Location> locationList = hibernateLocationDao.getAll();
        final int actualSize = locationList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(locationList.contains(testLocation1));

        final Location unexpectedLocation = hibernateLocationDao.findById(testLocation1.getId());
        assertNull(unexpectedLocation);
    }

    @Test
    void shouldGetAll() {
        final List<Location> locationList = hibernateLocationDao.getAll();

        assertEquals(2, locationList.size());
        assertTrue(locationList.contains(testLocation1));
        assertTrue(locationList.contains(testLocation2));
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }
}