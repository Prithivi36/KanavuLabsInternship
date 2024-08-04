package com.incubator.Virtual.Incubator.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {
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
    private String about;
    private List<Aspirant> aspirants;

}
