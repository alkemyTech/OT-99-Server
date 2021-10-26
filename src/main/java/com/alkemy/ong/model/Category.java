package com.alkemy.ong.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET DELETED = TRUE where id=?")
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractPersistable<Long> {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "image", nullable = true)
    private String image;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Category(Boolean isDeleted) {
        this.deleted = false;
    }

}
