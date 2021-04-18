package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Services;

import java.util.List;

public interface ServicesDao {

    Services create(Services services);
    Services update(Services services);
    Services findById(Long id);

    void delete(Services weatherMeasurements);

    List<Services> getAll();


}
