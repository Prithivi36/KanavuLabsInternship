package com.incubator.Virtual.Incubator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String state;
    private String district;
    private String position;
    private String type;
    private String organization;
    private int otp;
    private String about;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
     name = "mentorAspirant",
            joinColumns = @JoinColumn(name = "mentorId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aspirantId",referencedColumnName = "id")
    )
    @JsonIgnore
    private Set<Aspirant> aspirants;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private StartupProfile startupProfile;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "mnt",referencedColumnName = "id")
    @JsonIgnore
    private List<Requests> requests;
}
