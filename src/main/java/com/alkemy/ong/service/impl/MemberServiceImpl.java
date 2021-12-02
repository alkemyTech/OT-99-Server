package com.alkemy.ong.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.alkemy.ong.dto.PageDto;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberMapper memberMapper;

    @Override
    public void deleteMember(Long id) throws NotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The member of id:" + id + " was not found."));
        member.setDeletedDate(LocalDate.now());
        member.setDeleted(true);
        memberRepository.save(member);
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
        Member member = memberRepository.findById(id)
              .orElseThrow(() -> new NotFoundException("Member does not exist."));
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
    public PageDto<Member> getAllMembers(Integer pageNo, Integer pageSize, String sortBy) throws NotFoundException {
        if (pageSize <= 0 || pageNo < 0) {
            throw new NotFoundException(
                    "The size must be a positive integer. The page number must be a positive integer or zero.");
        }
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Member> pagedResult = memberRepository.findAll(paging);
        if (pageNo + 1 > pagedResult.getTotalPages()) {
            throw new NotFoundException("The page of number " + pageNo + " doesn't exist");
        }
        return createMemberPageDto(pagedResult);
    }

    private PageDto<Member> createMemberPageDto(Page<Member> page) {
        PageDto<Member> dto = new PageDto<>();
        dto.setList((page.getContent()));
        if (page.hasNext()) {
            dto.setNextPage("/members?page=" + page.nextPageable().getPageNumber());
        }
        if (page.hasPrevious()) {
            dto.setPreviousPage("/members?page=" + page.previousPageable().getPageNumber());
        }
        dto.setTotalPages(page.getTotalPages());
        return dto;
    }

}
