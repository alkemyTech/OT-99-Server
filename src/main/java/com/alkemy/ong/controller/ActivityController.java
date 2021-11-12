package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityRepository ar;

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody ActivityRequest activityRequest) throws EntityNotFoundException {
        Activity activity = activityService.updateActivity(id, activityRequest);
        return new ResponseEntity<>(activity, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Activity> create(@Valid @RequestBody ActivityRequest ar) throws DataAlreadyExistException{
        return new ResponseEntity<>(activityService.save(ar), HttpStatus.CREATED);
    }

}
