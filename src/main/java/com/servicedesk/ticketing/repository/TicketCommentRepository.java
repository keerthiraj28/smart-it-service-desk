package com.servicedesk.ticketing.repository;

import com.servicedesk.ticketing.entity.Ticket;
import com.servicedesk.ticketing.entity.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {

    List<TicketComment> findByTicket(Ticket ticket);
}
