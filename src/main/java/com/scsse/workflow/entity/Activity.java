package com.scsse.workflow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    private Date activityTime;
    @Column
    private String activityPlace;
    @Column
    private String activityDescription;
    @Column
    private Date activitySignUpDeadline;

    @ManyToMany
    @JoinTable(name = "activity_tag",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Activity(String activityName, Date activityTime, String activityPlace, String activityDescription, Date activitySignUpDeadline) {
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.activityPlace = activityPlace;
        this.activityDescription = activityDescription;
        this.activitySignUpDeadline = activitySignUpDeadline;
    }
}
