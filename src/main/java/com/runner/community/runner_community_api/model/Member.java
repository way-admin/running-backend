package com.runner.community.runner_community_api.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="member")
@Getter
@Setter
public class Member implements Serializable {
    private static final long serialVersionUID = 0x1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    @Column
    private String password;
    private String memberName;
    private int    age;
    private String address;
    private String roleGrant;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy="member")
    private List<MemberCrewMap> crewLists = new ArrayList<MemberCrewMap>();

    public Member() {}

    public Member(String email, String password, String memberName, int age, String address, String roleGrant
            , ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.age = age;
        this.address = address;
        this.roleGrant = roleGrant;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
