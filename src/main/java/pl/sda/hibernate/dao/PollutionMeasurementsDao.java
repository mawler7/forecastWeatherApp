package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.PollutionMeasurements;


import java.util.List;

public interface PollutionMeasurementsDao {



    PollutionMeasurements create(PollutionMeasurements pollutionMeasurements);

    PollutionMeasurements update(PollutionMeasurements pollutionMeasurements);

    PollutionMeasurements findById(Long id);


    void delete(PollutionMeasurements pollutionMeasurements);

    List<PollutionMeasurements> getAll();

}
