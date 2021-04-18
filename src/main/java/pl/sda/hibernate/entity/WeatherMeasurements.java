package pl.sda.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class WeatherMeasurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private double temperature;
    @Basic(optional = false)
    private double pressure;
    @Basic(optional = false)
    private double humidity;
    @Basic(optional = false)
    private double windSpeed;
    @Basic(optional = false)
    private double windDirection;

    public WeatherMeasurements(int temperature, double pressure, double humidity, double windSpeed, double windDirection) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public WeatherMeasurements(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherMeasurements that = (WeatherMeasurements) o;
        return Double.compare(that.temperature, temperature) == 0 &&
                pressure == that.pressure &&
                Double.compare(that.humidity, humidity) == 0 &&
                Double.compare(that.windSpeed, windSpeed) == 0 &&
                Objects.equals(windDirection, that.windDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, pressure, humidity, windSpeed, windDirection);
    }

    @Override
    public String toString() {
        return "WeatherMeasurements{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDirection='" + windDirection + '\'' +
                '}';
    }
}
