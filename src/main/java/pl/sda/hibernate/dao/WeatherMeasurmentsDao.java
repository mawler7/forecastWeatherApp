package pl.sda.hibernate.dao;


import pl.sda.hibernate.entity.WeatherMeasurements;

import java.util.List;

public interface WeatherMeasurmentsDao {

    WeatherMeasurements create(WeatherMeasurements weatherMeasurements);
    WeatherMeasurements update(WeatherMeasurements weatherMeasurements);
    WeatherMeasurements findById(Long id);

    void delete(WeatherMeasurements weatherMeasurements);

    List<WeatherMeasurements> getAll();

}
