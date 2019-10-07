package com.scsse.workflow.entity.model.relation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * mapping class for relation user_user
 *
 * @author Alfred Fu
 * Created on 2019/9/25 8:58 上午
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@IdClass(UserFollower.UserFollowerMultiKeysClass.class)
@Table(name = "user_follower")
class UserFollower {
    @Id
    @Column(name = "user_id")
    Integer userId;
    @Id
    @Column(name = "follower_id")
    Integer followerId;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    class UserFollowerMultiKeysClass implements Serializable {
        Integer userId;
        Integer followerId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserFollowerMultiKeysClass)) return false;
            UserFollowerMultiKeysClass that = (UserFollowerMultiKeysClass) o;
            return getUserId().equals(that.getUserId()) &&
                    getFollowerId().equals(that.getFollowerId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUserId(), getFollowerId());
        }
    }

}
