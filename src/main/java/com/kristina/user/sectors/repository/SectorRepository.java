package com.kristina.user.sectors.repository;

import com.kristina.user.sectors.model.Sector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sectorRepository")
public interface SectorRepository extends CrudRepository<Sector, Integer> {

    @Query("select s from Sector s where s.parentSector is null")
    List<Sector> getAllGeneralSectors();
}
