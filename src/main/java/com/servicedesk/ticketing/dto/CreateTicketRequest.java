package com.servicedesk.ticketing.dto;

import com.servicedesk.ticketing.entity.TicketPriority;

public class CreateTicketRequest {

    private Long userId;
    private String title;
    private String description;
    private TicketPriority priority;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public TicketPriority getPriority() {
        return priority;
    }
    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }
}
