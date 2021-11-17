package com.alkemy.ong.repository;

import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

	@Query("SELECT s FROM Slide s WHERE s.organizationId = :id ORDER BY s.slideOrder")
	List<Slide> findAllByOrganizationId(@Param("id") Organization org);
}
