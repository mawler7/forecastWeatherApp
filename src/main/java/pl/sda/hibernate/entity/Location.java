package pl.sda.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Basic(optional = false)
    Integer longitude;
    @Basic(optional = false)
    Integer latitude;
    String region;
    @Basic(optional = false)
    String city;
    @Basic(optional = false)
    String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(longitude, location.longitude) && Objects
                .equals(latitude, location.latitude) && Objects.equals(region, location.region) && Objects
                .equals(city, location.city) && Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, region, city, country);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
