package pl.sda.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private Integer longitude;
    @Basic(optional = false)
    private Integer latitude;
    private String region;
    @Basic(optional = false)
    private String city;
    @Basic(optional = false)
    private String country;

    public Location() {
    }

    public Location(Integer longitude, Integer latitude, String region, String city, String country) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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
