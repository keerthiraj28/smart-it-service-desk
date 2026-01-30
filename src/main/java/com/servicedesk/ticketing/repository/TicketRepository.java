package com.servicedesk.ticketing.repository;

import com.servicedesk.ticketing.entity.Ticket;
import com.servicedesk.ticketing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCreatedBy(User user);

    List<Ticket> findByAssignedTo(User user);
}
