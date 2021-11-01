package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "news")
@SQLDelete(sql = "UPDATE news SET news_deleted=true WHERE news_id=?")
@Where(clause = "news_deleted=false")

public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Long id;

	@Column(name = "news_name", nullable = false)
	private String name;

	@Column(name = "news_content", nullable = false)
	private String content;

	@Column(name = "news_image", nullable = false)
	private String image;

	@Column(name = "news_category_id", nullable = false)
	private Long categoryId;

	@Column(name = "news_creation_date", nullable = false)
	private LocalDate creationDate;

	@Column(name = "news_updated_date")
	private LocalDate updatedDate;

	@Column(name = "news_deleted_date")
	private LocalDate deletedDate;

	@Column(name = "news_deleted", nullable = false)
	private boolean deleted = Boolean.FALSE;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "news_category_id", insertable = false, updatable = false)
	private Category category;

}
