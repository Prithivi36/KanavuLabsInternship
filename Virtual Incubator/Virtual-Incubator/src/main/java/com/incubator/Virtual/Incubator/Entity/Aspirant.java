package com.incubator.Virtual.Incubator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aspirant {
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
    private String organization;
    private int otp;
    private String type;
    private String about;

    @ManyToMany(mappedBy = "aspirants")
    @JsonIgnore
    private Set<Mentor> mentors;

    @OneToOne()
    @JoinColumn(name = "id",referencedColumnName = "id")
    private StartupProfile startupProfile;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "asp",referencedColumnName = "id")
    @JsonIgnore
    private List<Requests> requests;
}
