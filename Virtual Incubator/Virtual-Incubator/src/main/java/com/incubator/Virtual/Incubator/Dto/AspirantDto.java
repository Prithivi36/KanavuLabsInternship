package com.incubator.Virtual.Incubator.Dto;

import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.Requests;
import com.incubator.Virtual.Incubator.Entity.StartupProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AspirantDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String state;
    private String type;
    private String district;
    private String position;
    private String organization;
    private int otp;
    private String about;
    private Set<Mentor> mentors;
    private StartupProfile startupProfile;
}
