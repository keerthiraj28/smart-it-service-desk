package com.servicedesk.ticketing.controller;

import com.servicedesk.ticketing.dto.CreateTicketRequest;
import com.servicedesk.ticketing.entity.*;
import com.servicedesk.ticketing.exception.BadRequestException;
import com.servicedesk.ticketing.exception.ResourceNotFoundException;
import com.servicedesk.ticketing.repository.TicketCommentRepository;
import com.servicedesk.ticketing.repository.TicketRepository;
import com.servicedesk.ticketing.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCommentRepository commentRepository;

    public TicketController(TicketRepository ticketRepository,
                            UserRepository userRepository,
                            TicketCommentRepository commentRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    @PostMapping
    public Ticket createTicketPost(@RequestBody CreateTicketRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setCreatedBy(user);

        return ticketRepository.save(ticket);
    }

    // Get all tickets
    @GetMapping("/all")
    public java.util.List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get tickets created by a user
    @GetMapping("/user/{userId}")
    public java.util.List<Ticket> getTicketsByUser(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ticketRepository.findByCreatedBy(user);
    }

    // Update ticket status
    @GetMapping("/update-status")
    public Ticket updateTicketStatus(@RequestParam Long ticketId,
                                     @RequestParam TicketStatus status) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }

    // Assign ticket to an agent
    @GetMapping("/assign")
    public Ticket assignTicket(@RequestParam Long ticketId,
                               @RequestParam Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));

        // Business rule: only AGENT can be assigned
        if (agent.getRole() != Role.AGENT) {
            throw new BadRequestException("User is not an AGENT");
        }

        ticket.setAssignedTo(agent);
        ticket.setStatus(TicketStatus.IN_PROGRESS);

        return ticketRepository.save(ticket);
    }

    @GetMapping("/comment")
    public TicketComment addComment(@RequestParam Long ticketId,
                                    @RequestParam Long userId,
                                    @RequestParam String comment) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TicketComment ticketComment = new TicketComment();
        ticketComment.setTicket(ticket);
        ticketComment.setCommentedBy(user);
        ticketComment.setComment(comment);

        return commentRepository.save(ticketComment);
    }

    @GetMapping("/{ticketId}/comments")
    public java.util.List<TicketComment> getComments(@PathVariable Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        return commentRepository.findByTicket(ticket);
    }

    @PutMapping("/{ticketId}/status")
    public Ticket updateStatusPut(@PathVariable Long ticketId,
                                  @RequestParam TicketStatus status) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    @PutMapping("/{ticketId}/assign/{agentId}")
    public Ticket assignAgentPut(@PathVariable Long ticketId,
                                 @PathVariable Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));

        if (agent.getRole() != Role.AGENT) {
            throw new BadRequestException("User is not an AGENT");
        }

        ticket.setAssignedTo(agent);
        ticket.setStatus(TicketStatus.IN_PROGRESS);

        return ticketRepository.save(ticket);
    }

}
