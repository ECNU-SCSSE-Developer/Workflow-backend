package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:06
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByOpenid(String openid);

    User findByUserId(Integer userId);

    @Query(" select count(f) from user_follower f where follower_id = :userId")
    Integer findFollowerNumberByUserId(@Param("userId") Integer userId);

    void deleteByUserId(Integer userId);

    boolean existsDistinctByOpenid(String openid);
}
