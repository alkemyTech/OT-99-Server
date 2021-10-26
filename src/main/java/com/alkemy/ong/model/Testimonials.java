package com.alkemy.ong.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "testimonials")
public class Testimonials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonials_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column
    private String content;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    
    @Column
    private boolean deleted = Boolean.FALSE;

    @Column(name = "delete_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

}
