package com.alkemy.ong.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import lombok.*;

@Getter
@Setter

@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE member_id=?")
@FilterDef(name = "deletedPostFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedPostFilter", condition = "deleted = : isDeleted")

public class Member { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id)")
    private Integer memberId;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private Timestamp timestamp;
    private boolean deleted = Boolean.FALSE;



    //--->Soft Delete en MySQL: deleted  bid(1)

    //---> Para implementar Soft delete en MemberController

    //***Se obtienen la lista de miembros que no han sido eliminados.
    /*@GetMapping("/members")
    public Iterable<Member> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return service.findAll(isDeleted);
    }*/


    //---> Para implementar Soft delete en MemberService

   /* public Iterable<Member> findAll(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedMemberFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Member> members =  repo.findAll();
        session.disableFilter("deletedMemberstFilter");
        return members;
    }*/


    
}
