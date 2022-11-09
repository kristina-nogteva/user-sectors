package com.kristina.user.sectors.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SECTOR")
public class Sector extends DomainObject_base {

    @Id()
    @Column(name = "ID", unique = true, nullable = false, length = 5)
    private Integer sectorId;

    @Column(name = "DESCRIPTION", length = 50, nullable = false, unique = false)
    private String description;

    @ManyToOne(targetEntity = Sector.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_SECTOR", referencedColumnName = "ID")
    private Sector parentSector;

    @OneToMany(targetEntity = Sector.class, fetch = FetchType.LAZY, mappedBy = "parentSector")
    private List<Sector> subCategories;

    public Integer getId() {
        return sectorId;
    }

    public String getDescription() {
        return description;
    }

    public List<Sector> getSubCategories() {
        return subCategories;
    }

    public Sector getParentSector() {
        return parentSector;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object instanceof Sector) return getId().equals(((Sector) object).getId());
        return false;
    }
}
