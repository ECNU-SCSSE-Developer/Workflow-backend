package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 11:24
 */
@Getter
@Setter
@ToString(exclude = {"users","activities","recruits"})
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


    @ManyToMany(mappedBy = "userTags")
    @JsonBackReference(value = "activity.users")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "activityTags")
    @JsonBackReference(value = "activity.activities")
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(mappedBy = "recruitTags")
    @JsonBackReference(value = "activity.recruits")
    private Set<Recruit> recruits = new HashSet<>();

    public Tag(String tagName, String tagDescription) {
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getTagId() == tag.getTagId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTagId());
    }
}
