package com.scsse.workflow.entity;

import lombok.Getter;
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
    @JoinTable(name = "User_Tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;


}
