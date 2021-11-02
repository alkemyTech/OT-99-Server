package com.alkemy.ong.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE organization SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_name", nullable = false)
    private String name;

    @Column(name = "org_image_url", nullable = false)
    private String image;

    @Column(name = "org_address")
    private String address;

    @Column(name = "org_phone")
    private int phone;

    @Column(name = "org_email", nullable = false)
    private String email;

    @Column(name = "org_welcome_text", nullable = false, columnDefinition = "Text", length = 65535)
    private String welcomeText;


    @Column(name = "org_about_us_text", columnDefinition = "Text", length = 65535)
    private String aboutUsText;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
