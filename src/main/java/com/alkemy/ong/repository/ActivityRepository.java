package com.alkemy.ong.repository;

import java.util.Optional;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Long>{

    Optional<Activity> findByName(String name);

    Activity save(ActivityRequest activityReq);

}
