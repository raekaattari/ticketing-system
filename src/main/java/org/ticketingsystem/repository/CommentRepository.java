package org.ticketingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ticketingsystem.model.Comment;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query(value = "SELECT * FROM comment where ticket_id = :ticketId", nativeQuery = true)
    List<Comment> findAllByTicketId(UUID ticketId);
}
