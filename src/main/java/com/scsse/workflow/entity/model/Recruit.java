package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 11:07
 */

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "recruit")
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int recruitId;
    @Column
    private String recruitName;
    @Column
    private String recruitPosition;
    @Column
    private String recruitDescription;
    @Column
    private String recruitState;
    @Column
    private int recruitWillingNumber;
    @Column
    private int recruitRegisteredNumber;
    @Column
    private Date createTime = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne
    @JsonBackReference(value = "recruit.team")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "recruit.recruitTags")
    @JoinTable(name = "recruit_tag",
            joinColumns = @JoinColumn(name = "recruit_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> recruitTags = new HashSet<>();


    @ManyToMany
    @JsonBackReference(value = "recruit.members")
    @JoinTable(name = "recruit_member",
            joinColumns = @JoinColumn(name = "recruit_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members = new HashSet<>();


    @ManyToMany(mappedBy = "applyRecruits")
    @JsonBackReference(value = "recruit.applicants")
    private Set<User> applicants = new HashSet<>();


    @ManyToMany(mappedBy = "followRecruits")
    @JsonBackReference(value = "recruit.followers")
    private Set<User> followers = new HashSet<>();


    public Recruit(String recruitName, Activity activity) {
        this.recruitName = recruitName;
        this.activity = activity;
    }

    public Recruit(String recruitName, String recruitPosition, String recruitDescription, String recruitState, int recruitWillingNumber, int recruitRegisteredNumber, User manager, Activity activity) {
        this.recruitName = recruitName;
        this.recruitPosition = recruitPosition;
        this.recruitDescription = recruitDescription;
        this.recruitState = recruitState;
        this.recruitWillingNumber = recruitWillingNumber;
        this.recruitRegisteredNumber = recruitRegisteredNumber;
        this.manager = manager;
        this.activity = activity;
    }
}



