package com.alkemy.ong.service.impl;

import java.time.LocalDate;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.service.MemberService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void deleteMember(Long id) throws NotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The member of id:" + id + " was not found."));
        member.setDeletedDate(LocalDate.now());
        member.setDeleted(true);
        memberRepository.save(member);
    }

}
