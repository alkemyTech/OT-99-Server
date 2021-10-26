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


    
}
