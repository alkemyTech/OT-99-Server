package com.alkemy.ong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE slide SET DELETED = TRUE where id=?")
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;
    @Column(name = "TEXT", columnDefinition = "Text")
    private String text;
    @Column(name = "SLIDE_ORDER", nullable = false)
    private Integer slideOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SLIDE_ORGANIZATION_ID")
    private Organization organizationId;

}
