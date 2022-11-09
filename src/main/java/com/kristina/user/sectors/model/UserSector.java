package com.kristina.user.sectors.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_SECTOR")
@SequenceGenerator(name = "userSectorSequenceGenerator", sequenceName = "USER_SECTOR_SEQUENCE", allocationSize = 1)
public class UserSector {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSectorSequenceGenerator")
    @Column(name = "ID", unique = true, nullable = false, length = 3)
    private Integer id;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME")
    private User user;

    @ManyToOne(targetEntity = Sector.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "SECTOR", referencedColumnName = "ID")
    private Sector sector;

    public UserSector(){

    }

    public UserSector(User user, Sector sector){
        this.user = user;
        this.sector = sector;
    }

    public Sector getSector() {
        return sector;
    }
}
