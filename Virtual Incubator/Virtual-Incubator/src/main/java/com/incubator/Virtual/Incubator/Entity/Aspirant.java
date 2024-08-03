package com.incubator.Virtual.Incubator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String otp;
    private String about;
    @ManyToMany(mappedBy = "aspirants")
    @JsonIgnore
    private List<Mentor> mentors;
}
