package org.ticketingsystem.service;

import org.springframework.stereotype.Service;
import org.ticketingsystem.model.Comment;
import org.ticketingsystem.model.Ticket;
import org.ticketingsystem.repository.CommentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        comment.setCommentId(UUID.randomUUID());
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getCommentsByTicket(UUID ticketId) {
        return commentRepository.findAllByTicketId(ticketId);
    }
}
