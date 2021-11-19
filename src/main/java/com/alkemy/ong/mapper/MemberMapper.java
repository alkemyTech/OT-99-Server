package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    @Autowired
    ModelMapper modelMapper;

    public Member dtoToEntity(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return member;

    }
}