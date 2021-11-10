package com.alkemy.ong.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity

@SQLDelete(sql = "UPDATE category SET DELETED = TRUE where id=?")
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "image", nullable = true)
    private String image;
    @Column(name = "deleted")
    private Boolean deleted = false;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
