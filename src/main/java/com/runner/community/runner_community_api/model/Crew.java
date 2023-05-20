package com.runner.community.runner_community_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="crew")
@Getter
@Setter
public class Crew implements Serializable {
    private static final long serialVersionUID = 0x2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crewId")
    private Long crewId;

    @Column(unique = true)
    private String crewName;

    @Column
    private String area;
    private int minAge;
    private int maxAge;
    private double bestRecord;
    private double avgRecord;
    private double totalRecord;

    @OneToMany(mappedBy = "crew")
    private List<MemberCrewMap> memberCrewMaps = new ArrayList<MemberCrewMap>();
}
