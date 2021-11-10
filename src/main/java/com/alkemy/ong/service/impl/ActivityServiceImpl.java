package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepo;
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Activity updateActivity(Long id, ActivityRequest activityReq) throws EntityNotFoundException {
        Activity activity = activityRepo.findById(id).get();
        activity.setUpdateDateTime(LocalDateTime.now());
        activityMapper.dtoToEntity(activityReq, activity);
        return activityRepo.save(activity);

    }

}
