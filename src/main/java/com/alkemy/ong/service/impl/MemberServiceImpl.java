package com.alkemy.ong.service.impl;

import javax.validation.Valid;

import java.time.LocalDate;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository repo;

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member save(@Valid MemberDto mDto) throws DataAlreadyExistException {
        if ((repo.findByName(mDto.getName()).isPresent())) {
            throw new DataAlreadyExistException("Sorry!, Member already exists.");
        }
        Member member = memberMapper.dtoToEntity(mDto);
        member.setCreationDate(LocalDate.now());
        member.setUpdatedDate(LocalDate.now());
        return repo.save(member);
    }
}
