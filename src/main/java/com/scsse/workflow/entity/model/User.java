package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 10:42
 */


@Getter
@Setter
@ToString(exclude = {"followRecruits", "successRecruits", "applyRecruits", "followUser"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String college;
    @Column
    private String userGrade;
    @Column
    private String userPhone;
    @Column
    private String userEmail;
    @Column
    private String wxId;
    @Column
    private String userSpecialty;
    @Column
    private String userResume;
    @Column
    private String openid;

    @OneToMany
    @JsonBackReference(value = "user.followUsers")
    @JoinTable(name = "user_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<User> followUser = new HashSet<>();

    @OneToMany
    @JsonBackReference(value = "user.followActivities")
    @JoinTable(name = "user_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private Set<Activity> followActivities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "user.userTags")
    @JoinTable(name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> userTags = new HashSet<>();

    @ManyToMany
    @JsonBackReference(value = "user.followRecruits")
    @JoinTable(name = "user_recruit_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recruit_id"))
    private Set<Recruit> followRecruits = new HashSet<>();

    @ManyToMany
    @JsonBackReference(value = "user.applyRecruits")
    @JoinTable(name = "user_recruit_applicant",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recruit_id"))
    private Set<Recruit> applyRecruits = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    @JsonBackReference(value = "user.successRecruits")
    private Set<Recruit> successRecruits = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    @JsonBackReference(value = "user.joinedTeam")
    private Set<Team> joinedTeam = new HashSet<>();

    public User(String username, String openid) {
        this.username = username;
        this.openid = openid;
    }

    public User(String openid) {
        this.openid = openid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getUserNumber(), user.getUserNumber()) &&
                Objects.equals(getUserGrade(), user.getUserGrade()) &&
                Objects.equals(getUserPhone(), user.getUserPhone()) &&
                Objects.equals(getUserEmail(), user.getUserEmail()) &&
                Objects.equals(getUserSpecialty(), user.getUserSpecialty()) &&
                Objects.equals(getUserResume(), user.getUserResume()) &&
                Objects.equals(getOpenid(), user.getOpenid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getUserNumber(), getUserGrade(), getUserPhone(), getUserEmail(), getUserSpecialty(), getUserResume(), getOpenid());
    }
}
