package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019/9/25 9:30 上午
 */

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer teamId;

    @OneToOne
    @JoinColumn(name = "graph_id")
    private Graph graph;

    @ManyToMany
    @JoinTable(
            name = "team_member",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference(value = "team.members")
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "team")
    @JsonBackReference(value = "team.recruitSet")
    private Set<Recruit> recruits = new HashSet<>();
}
