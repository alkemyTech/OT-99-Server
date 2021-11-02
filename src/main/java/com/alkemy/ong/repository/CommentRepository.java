
package com.alkemy.ong.repository;

import com.alkemy.ong.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
