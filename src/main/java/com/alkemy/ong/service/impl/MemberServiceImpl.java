package com.alkemy.ong.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepositoryMock) {
	}

	@Override
    public Optional<Long> deleteMember(Long id) throws NotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The member of id:" + id + " was not found."));
        member.setDeletedDate(LocalDate.now());
        member.setDeleted(true);
        memberRepository.save(member);
        return null;
    }

    @Override
    public Member save(@Valid MemberDto mDto) throws DataAlreadyExistException {
        if ((memberRepository.findByName(mDto.getName()).isPresent())) {
            throw new DataAlreadyExistException("Sorry! Member already exists.");
        }
        Member member = memberMapper.dtoToEntity(mDto);
        member.setCreationDate(LocalDate.now());
        member.setUpdatedDate(LocalDate.now());
        return memberRepository.save(member);
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) throws NotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException("Member does not exist."));
        member.setName(memberDto.getName());
        member.setFacebook(memberDto.getFacebook());
        member.setInstagram(memberDto.getInstagram());
        member.setLinkedin(memberDto.getLinkedin());
        member.setImage(memberDto.getImage());
        member.setDescription(memberDto.getDescription());
        member.setUpdatedDate(LocalDate.now());
        return MemberMapper.mapToDto(memberRepository.save(member));
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }



    @Override
    public Optional<Member> updateMember(Long id, String name, String facebook, String instagram, String linkedin,
            String image, String description) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Member> getMembers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object save(Member memberMock4WithOutName) {
        // TODO Auto-generated method stub
        return null;
    }
}

