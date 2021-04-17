package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Location;

import java.util.List;

public interface LocationDao {
    // CRUD
    Location create(Location location);
    Location update(Location location);
    Location findById(Long id);
    void delete(Location location);

    List<Location> getAll();
}
