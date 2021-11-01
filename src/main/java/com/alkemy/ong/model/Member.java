package com.alkemy.ong.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@SQLDelete(sql = "UPDATE member SET member_deleted = true WHERE member_id=?")
@Where(clause = "member_deleted = false")

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_facebook_URL")
    private String facebookUrl;

    @Column(name = "member_instagram_URL")
    private String instagramUrl;

    @Column(name = "member_linkedin_URL")
    private String linkedinUrl;

    @Column(name = "member_image", nullable = false)
    private String image;

    @Column(name = "member_description")
    private String description;

    @Column(name = "member_creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "member_updated_date")
    private LocalDate updatedDate;

    @Column(name = "member_deleted_date")
    private LocalDate deletedDate;

    @Column(name = "member_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

}