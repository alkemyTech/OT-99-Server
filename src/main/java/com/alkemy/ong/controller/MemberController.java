package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) throws NotFoundException {
        memberService.deleteMember(id);
        return new ResponseEntity<>("The member was deleted successfully.", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody MemberDto mDto) throws DataAlreadyExistException {
        return new ResponseEntity<>(memberService.save(mDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) throws NotFoundException {
        return new ResponseEntity<>(memberService.updateMember(memberDto,id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

}

