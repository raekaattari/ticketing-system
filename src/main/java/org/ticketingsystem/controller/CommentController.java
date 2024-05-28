package org.ticketingsystem.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ticketingsystem.model.Comment;
import org.ticketingsystem.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /* Create new comment */
    @RequestMapping(value = {"/ticketing-system/comments"}, method = RequestMethod.POST)
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    /* Get all comments for an event */
    @RequestMapping(value = {"/ticketing-system/comments/{ticketId}"}, method = RequestMethod.GET)
    public List<Comment> getAllCommentsByTicket(@PathVariable("ticketId") UUID ticketId) {
        return commentService.getCommentsByTicket(ticketId);
    }

}
