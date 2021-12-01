package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.dto.CommentDtoSave;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<CommentDto> getAll() {

        List<Comment> comments = commentRepository.findAll(Sort.by(Direction.DESC, "creationDate"));

        return commentMapper.toCommentDtoList(comments);
    }

    @Override
    public CommentDtoResponse save(CommentDtoSave commentDtoSave) throws NotFoundException {
        Users users = userRepository.findById(commentDtoSave.getUserId()).orElseThrow(() -> new NotFoundException("User does not exist"));
        News news = newsRepository.findById(commentDtoSave.getPostId()).orElseThrow(() -> new NotFoundException("Post does not exist"));
        Comment comment = new Comment();
        comment.setContent(commentDtoSave.getBody());
        comment.setNews(news);
        comment.setUser(users);
        comment.setCreationDate(LocalDateTime.now());
        return CommentDtoResponse.mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDtoResponse> getCommentbyNewsId(Long id) throws NotFoundException {
        if (!newsRepository.existsById(id)) {
            throw new NotFoundException("News does not exist");
        }
        List<Comment> comment = commentRepository.getCommentbyNews(id);
        List<CommentDtoResponse> commentDto
                = comment.stream().map(CommentDtoResponse::mapToDto)
                        .collect(Collectors.toList());
        return commentDto;

    }

	@Override
	public CommentDto update(CommentDto commentDto, Long id) throws NotFoundException {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("The comment don't exist"));
		comment.setContent(commentDto.getContent());
		return CommentMapper.mapToDto(commentRepository.save(comment));
	}

}
