package com.scsse.workflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 10:42
 */


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;
    @Column
    private String username;
    @Column
    private String userNumber;
    @Column
    private String userGrade;
    @Column
    private String userPhone;
    @Column
    private String userEmail;
    @Column
    private String userSpecialty;
    @Column
    private String userResume;

    @ManyToMany
    @JoinTable(name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;


    @ManyToMany
    @JoinTable(name = "user_recruit_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recruit_id"))
    private Set<Recruit> followRecruits;

    @ManyToMany
    @JoinTable(name = "user_recruit_applicant",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recruit_id"))
    private Set<Recruit> applyRecruits;


    @JsonBackReference
    @ManyToMany(mappedBy = "members")
    private Set<Recruit> successRecruits;

    public User(String username, String userNumber, String userGrade, String userPhone, String userEmail, String userSpecialty, String userResume) {
        this.username = username;
        this.userNumber = userNumber;
        this.userGrade = userGrade;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userSpecialty = userSpecialty;
        this.userResume = userResume;
    }
}
