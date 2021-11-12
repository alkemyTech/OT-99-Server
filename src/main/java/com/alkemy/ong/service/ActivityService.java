
package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;



public interface ActivityService {
    Activity updateActivity(Long id, ActivityRequest activityReq) throws EntityNotFoundException;

    Activity save(@Valid ActivityRequest ar) throws DataAlreadyExistException;
}
