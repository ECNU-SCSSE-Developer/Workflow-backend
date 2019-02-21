package com.scsse.workflow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 11:24
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int tagId;
    @Column
    private String tagName;
    @Column
    private String tagDescription;


    @ManyToMany(mappedBy = "tags")
    private Set<User> users;

    @ManyToMany(mappedBy = "tags")
    private Set<Activity> activities;

    @ManyToMany(mappedBy = "tags")
    private Set<Recruit> recruits;

    public Tag(String tagName, String tagDescription) {
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }
}
