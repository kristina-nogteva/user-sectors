package com.kristina.user.sectors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SECTOR")
public class Sector implements Serializable {

    @Id()
    @Column(name = "ID", unique = true, nullable = false, length = 3)
    Integer sectorId;

    @Column(name = "DESCRIPTION", length = 50, nullable = false, unique = false)
    private String description;

    public String getDescription() {
        return description;
    }
}
