package com.scsse.workflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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
    private String recruitPosition;
    @Column
    private String recruitDescription;
    @Column
    private String recruitState;
    @Column
    private int recruitWillingNumber;
    @Column
    private int recruitRegisteredNumber;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User manager;

    @OneToOne
    @JoinColumn(name = "activity_id",unique = true)
    private Activity activity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "tags")
    @JoinTable(name = "recruit_tag",
            joinColumns = @JoinColumn(name = "recruit_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;


    @ManyToMany
    @JsonBackReference(value = "members")
    @JoinTable(name="recruit_member",
            joinColumns = @JoinColumn(name = "recruit_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;


    @JsonBackReference(value = "applicants")
    @ManyToMany(mappedBy = "applyRecruits")
    private Set<User> applicants;


    @ManyToMany(mappedBy = "followRecruits")
    @JsonBackReference(value = "followers")
    private Set<User> followers;

    public Recruit(String recruitPosition, String recruitDescription, String recruitState, int recruitWillingNumber, int recruitRegisteredNumber, User manager, Activity activity) {
        this.recruitPosition = recruitPosition;
        this.recruitDescription = recruitDescription;
        this.recruitState = recruitState;
        this.recruitWillingNumber = recruitWillingNumber;
        this.recruitRegisteredNumber = recruitRegisteredNumber;
        this.manager = manager;
        this.activity = activity;
    }
}



