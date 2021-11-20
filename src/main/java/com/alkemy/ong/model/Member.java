package com.alkemy.ong.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@SQLDelete(sql = "UPDATE member SET member_deleted = true WHERE member_id=?")
@Where(clause = "member_deleted = false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_facebook_url")
    private String facebook;

    @Column(name = "member_instagram_url")
    private String instagram;

    @Column(name = "member_linkedin_url")
    private String linkedin;

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
