package com.alkemy.ong.mapper;

import com.alkemy.ong.model.Member;

import java.time.LocalDate;

public class MemberMapper {
    public static MemberDto mapToDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setDescription(member.getDescription());
        memberDto.setFacebook(member.getFacebookUrl());
        memberDto.setInstagram(member.getInstagramUrl());
        memberDto.setLinkedin(member.getLinkedinUrl());
        memberDto.setImage(member.getImage());
        memberDto.setName(member.getName());
        memberDto.setCreationDate(member.getCreationDate());
        memberDto.setUpdatedDate(LocalDate.now());
        return memberDto;
    }
}
