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


    @JsonBackReference(value = "users")
    @ManyToMany(mappedBy = "tags")
    private List<User> users;

    @JsonBackReference(value = "activities")
    @ManyToMany(mappedBy = "tags")
    private List<Activity> activities;

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference(value = "recruits")
    private List<Recruit> recruits;

    public Tag(String tagName, String tagDescription) {
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }
}
