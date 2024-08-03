package com.incubator.Virtual.Incubator.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "funding_to",referencedColumnName = "id")
    private List<Funding> fundingDetail;




}
