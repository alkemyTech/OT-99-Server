package com.alkemy.ong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.*;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Entity

@SQLDelete(sql = "UPDATE Contact SET contact_deleted_at=true WHERE contact_id=?")
@Where(clause = "contact_deleted_at=false")

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "contact_name")
    private String name;

    @Column(name = "contact_phone")
    private Double phone;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_message")
    private String message;

    @Column(name = "contact_deleted_at", nullable = false)
    private boolean deletedAt = Boolean.FALSE;

}
