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
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_name",nullable = false)
    private String name;

    @Column(name = "activity_content",nullable = false, columnDefinition = "Text", length = 65535)
    private String content;

    @Column(name = "activity_image_url",nullable = false)
    private String image;

    @Column(name = "is_deleted",nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(name ="creation_date",nullable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "last_update_date",nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
}
