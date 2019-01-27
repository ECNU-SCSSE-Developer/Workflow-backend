package com.scsse.workflow.entity;

import lombok.Getter;
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
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer activityId;
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
    @JoinTable(name = "Activity_Tag",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public Set<Tag> tags;
}
