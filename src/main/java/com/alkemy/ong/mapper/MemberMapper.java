package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MemberMapper {

    @Autowired
    ModelMapper modelMapper;

    public Member dtoToEntity(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return member;

    }

    public static MemberDto mapToDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setFacebook(member.getFacebook());
        memberDto.setDescription(member.getDescription());
        memberDto.setInstagram(member.getInstagram());
        memberDto.setImage(member.getImage());
        memberDto.setLinkedin(member.getLinkedin());
        memberDto.setCreationDate(member.getCreationDate());
        memberDto.setName(member.getName());
        memberDto.setUpdatedDate(LocalDate.now());
        return memberDto;
    }
}
