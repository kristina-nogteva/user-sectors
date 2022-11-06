package com.kristina.user.sectors.repository;

import com.kristina.user.sectors.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("sectorRepository")
public interface SectorRepository extends CrudRepository<Sector, Integer> {
}
