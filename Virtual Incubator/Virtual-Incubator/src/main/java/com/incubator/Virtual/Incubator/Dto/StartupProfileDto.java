package com.incubator.Virtual.Incubator.Dto;

import com.incubator.Virtual.Incubator.Entity.Funding;
import com.incubator.Virtual.Incubator.Entity.StartupProfile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupProfileDto {
    private Long id;
    private String startupName;
    private String startupDescription;
    private String startupStage;
    private String startupDomain;
    private String founderName;
    private String founderEmail;
    private String founderContact;
    private LocalDate launchDate;
    private int numberOfEmployees;
    private String status;
    private int totalFunding;
    private List<String> feedbacks;
    private List<Funding> fundingDetail;
    private StartupProfile startupProfile;
}
