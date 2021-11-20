package com.alkemy.ong.service;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Member;

import java.util.List;

public interface MemberService {

    void deleteMember(Long id) throws NotFoundException;

    Member save(@Valid MemberDto mDto) throws DataAlreadyExistException;

    public List<Member> findAll();

}

