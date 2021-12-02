package com.alkemy.ong.service;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Long> deleteMember(Long id) throws NotFoundException;

    public MemberDto updateMember(MemberDto memberDto, Long id) throws NotFoundException;

    public List<Member> findAll();

    Optional<Member> updateMember(Long id, String name, String facebook, String instagram, String linkedin, String image,
            String description);

    List<Member> getMembers();

    Member save(@Valid MemberDto mDto) throws DataAlreadyExistException;

    PageDto<Member> getAllMembers(Integer pageNo, Integer pageSize, String sortBy)throws NotFoundException;

}

