
package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.model.Activity;
import javax.persistence.EntityNotFoundException;



public interface ActivityService {
    Activity updateActivity(Long id, ActivityRequest activityReq) throws EntityNotFoundException;
}
