package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:06
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByOpenid(String openid);

    User findByUserId(Integer userId);

    @Query(" select count(f) from UserFollower f where f.followerId = :userId")
    Integer findFollowerNumberByUserId(@Param("userId") Integer userId);

    @Query("select u from User u where u.userId in(select f.userId from UserFollower f where f.followerId = :userId)")
    List<User> findUserFollower(@Param("userId") Integer userId);

    void deleteByUserId(Integer userId);

    boolean existsDistinctByOpenid(String openid);
}
