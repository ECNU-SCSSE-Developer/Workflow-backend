package com.scsse.workflow.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 11:07
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "recruit")
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer recruitId;
    @Column
    private String recruitPosition;
    @Column
    private String recruitDescription;
    @Column
    private String recruitState;
    @Column
    private Integer recruitWillingNumber;
    @Column
    private Integer recruitRegisteredNumber;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User manager;

    @OneToOne
    @JoinColumn(name = "activity_id",unique = true)
    private Activity activity;

    @ManyToMany
    @JoinTable(name = "Recruit_Tag",
            joinColumns = @JoinColumn(name = "recruit_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;


}



