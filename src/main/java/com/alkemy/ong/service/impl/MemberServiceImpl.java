package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) throws NotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException("The member don't exists."));
        member.setName(memberDto.getName());
        member.setFacebookUrl(memberDto.getFacebook());
        member.setInstagramUrl(memberDto.getInstagram());
        member.setLinkedinUrl(memberDto.getLinkedin());
        member.setImage(memberDto.getImage());
        member.setDescription(memberDto.getDescription());
        member.setUpdatedDate(LocalDate.now());
        return MemberMapper.mapToDto(memberRepository.save(member));
    }
}
