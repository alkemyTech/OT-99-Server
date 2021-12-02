package com.alkemy.ong.service;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Long> deleteMember(Long id) throws NotFoundException;

    //Optional<Long> save(@Valid MemberDto mDto) throws DataAlreadyExistException;

    public MemberDto updateMember(MemberDto memberDto, Long id) throws NotFoundException;

    public List<Member> findAll();

    Optional<Member> updateMember(Long id, String name, String facebook, String instagram, String linkedin, String image,
            String description);

    List<Member> getMembers();

    Member save(@Valid MemberDto mDto) throws DataAlreadyExistException;

    Object save(Member memberMock4WithOutName);


}

