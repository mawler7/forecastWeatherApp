package pl.sda.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.HibernateLocationDao;
import pl.sda.hibernate.dao.LocationDao;
import pl.sda.hibernate.dao.PollutionListing;
import pl.sda.hibernate.dao.WeatherListing;
import pl.sda.hibernate.entity.Location;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class HibernateApp {

    private static SessionFactory sessionFactory;
    private static LocationDao locationDao;

    public static void main(String[] args) throws IOException, URISyntaxException {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Location.class)
                .buildSessionFactory();
        locationDao = new HibernateLocationDao(sessionFactory);

        System.out.println("\n\n===READY?===");


        WeatherListing.getCity();
        System.out.print("|City: ");
        WeatherListing.getCity();
        System.out.print("|Temperature: ");
        WeatherListing.getTemperature();
        System.out.print("|Pressure: ");
        WeatherListing.getPressure();
        System.out.print("|Humidity: ");
        WeatherListing.getHumidity();
        System.out.print("|Wind speed: ");
        WeatherListing.getWindSpeed();
        System.out.print("|Wind direction: ");
        WeatherListing.getWindDirection();
        System.out.println("=========================");
       // System.out.print("|City Latitude: ");
       // WeatherListing.getLatitude();
       // System.out.print("|City Longitude: ");
       // WeatherListing.getLongitude();
        System.out.println("=========================");
        System.out.print("|City CarbonMonoxide: ");
        PollutionListing.getCarbonMonoxide();
        System.out.print("|City NitrogenMonoxide: ");
        PollutionListing.getNitrogenMonoxide();
        System.out.print("|City NitrogenDioxide: ");
        PollutionListing.getNitrogenDioxide();
        System.out.print("|City Ozone: ");
        PollutionListing.getOzone();
        System.out.print("|City Sulphur Dioxide: ");
        PollutionListing.getSulphurDioxide();
        System.out.print("|City Ammonia: ");
        PollutionListing.getAmmonia();
        System.out.print("|City FineParticulateMatter: ");
        PollutionListing.getFineParticulateMatter();
        System.out.print("|City CoarseParticulateMatter: ");
        PollutionListing.getCoarseParticulateMatter();
        System.out.println("=========================");
        System.out.println("<<<<END END END END>>>>>");


    }

    private static void createNewLocation() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Nowa lokalizacja");
        System.out.println("Podaj miasto");
        final String city = scanner.nextLine();
        System.out.println("Podaj region");
        final String region = scanner.nextLine();
        System.out.println("Podaj państwo");
        final String country = scanner.nextLine();
        System.out.println("Podaj długość geograficzną");
        final int longitude = scanner.nextInt();
        System.out.println("Podaj szerokość geograficzną");
        final int latitude = scanner.nextInt();

        final Location location = new Location(longitude, latitude, region, city, country);

        locationDao.create(location);
    }

    private static void listAllLocations() {
        final List<Location> locationList = locationDao.getAll();
        locationList.forEach(location -> {
            System.out.println("Localizacja:");
            System.out.println("  miasto:" + location.getCity());
            System.out.println("  region:" + location.getRegion());
            System.out.println("  country:" + location.getCountry());
            System.out.println("  długość geograficzna:" + location.getLongitude());
            System.out.println("  szerokość geograficzna:" + location.getLatitude());
        });
    }

}