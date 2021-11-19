package com.alkemy.ong.service;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Member;

public interface MemberService {

    Member save(@Valid MemberDto mDto) throws DataAlreadyExistException;

}