package com.kristina.user.sectors.model;


import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER")
public class User extends DomainObject_base {

    @Id()
    @NotBlank(message = "Please enter username")
    @Column(name = "USER_NAME", unique = true, nullable = false, length = 25)
    private String username;

    @NotBlank(message = "Please enter password")
    @Column(name = "PASSWORD", unique = true, nullable = false, length = 50)
    private String password;

    @NotBlank(message = "Please enter your name")
    @Column(name = "FULL_NAME", unique = true, nullable = false, length = 50)
    private String fullName;

    @NotEmpty(message = "Please select at least one sector")
    @OneToMany(targetEntity = UserSector.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSector> userSectors;

    @AssertTrue(message = "Username can only contain letters and numbers")
    public boolean isUsernameValid(){
        return username.matches("[a-zA-Z0-9]*");
    }

    @AssertTrue(message = "Name must only contain letters")
    public boolean isFullNameValid(){
        return fullName.trim().chars().allMatch(Character::isLetter);
    }

    @Override
    public Object getId() {
        return username;
    }

    public void setSectors(List<Sector> sectors){
        userSectors = sectors.stream().map(sector -> new UserSector(this, sector)).collect(Collectors.toList());
    }

    public List<Sector> getSectors(){
        if (userSectors == null) userSectors = new ArrayList<>();
        return userSectors.stream().map(userSector -> userSector.getSector()).collect(Collectors.toList());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
