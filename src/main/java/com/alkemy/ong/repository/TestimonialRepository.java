package com.alkemy.ong.repository;

import com.alkemy.ong.model.Testimonial;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository <Testimonial, Long> {

    Optional <Testimonial> findByName(String name);
       
}
