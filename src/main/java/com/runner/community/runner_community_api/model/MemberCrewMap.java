package com.runner.community.runner_community_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="userCrewMap")
@Getter
@Setter
public class MemberCrewMap {
    private static final long serialVersionUID = 0x3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    @Column
    private boolean admin;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "crewId")
    private Crew crew;
}
