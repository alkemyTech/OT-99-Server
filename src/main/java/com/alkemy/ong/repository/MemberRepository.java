package com.alkemy.ong.repository;

import java.util.Optional;

import com.alkemy.ong.model.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    Optional<Member> findByName(String name);
  

}