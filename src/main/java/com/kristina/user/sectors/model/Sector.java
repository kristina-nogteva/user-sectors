package com.kristina.user.sectors.model;

import antlr.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

@Entity
@Table(name = "SECTOR")
public class Sector implements Serializable {

    @Id()
    @Column(name = "ID", unique = true, nullable = false, length = 3)
    Integer sectorId;

    @Column(name = "DESCRIPTION", length = 50, nullable = false, unique = false)
    private String description;

    @ManyToOne(targetEntity = Sector.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_SECTOR", referencedColumnName = "ID")
    private Sector parentSector;

    @OneToMany(targetEntity = Sector.class, fetch = FetchType.LAZY, mappedBy = "parentSector")
    private Set<Sector> subCategories;

    public String getDescription() {
        return description;
    }

    public Set<Sector> getSubCategories() {
        return subCategories;
    }

    public Sector getParentSector() {
        return parentSector;
    }
}
