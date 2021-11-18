package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/members")

public class MemberController {

    @Autowired
    private MemberService service;

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody MemberDto mDto) throws DataAlreadyExistException {
        return new ResponseEntity<>(service.save(mDto), HttpStatus.CREATED);
    }
}