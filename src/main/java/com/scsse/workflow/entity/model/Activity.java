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
 * Created on 2019-01-27 11:03
 */

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int activityId;
    @Column
    private String activityName;
    @Column
    private Date activityTime = new Date();
    @Column
    private String activityPlace;
    @Column
    private String activityDescription;
    @Column
    private Date activitySignUpDeadline = new Date();
    @Column
    private String activityUrl;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JsonBackReference(value = "activity.activityTags")
    @JoinTable(name = "activity_tag",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> activityTags = new HashSet<>();

    public Activity(String activityName, Date activityTime, String activityPlace, String activityDescription, Date activitySignUpDeadline) {
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.activityPlace = activityPlace;
        this.activityDescription = activityDescription;
        this.activitySignUpDeadline = activitySignUpDeadline;
    }

    public Activity(String activityName) {
        this.activityName = activityName;
    }
}
