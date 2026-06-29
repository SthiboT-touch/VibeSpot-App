package com.nocturne.app.model;

import java.time.LocalDateTime;
import java.util.List;

public class Event {

    private final Long id;
    private final String title;
    private final Long venueId;
    private final String venueName;
    private final String category;
    private final LocalDateTime startsAt;
    private final String description;
    private final String priceLabel;
    private final List<String> tags;
    private final String accentClass;
    private int interestedCount;

    public Event(Long id, String title, Long venueId, String venueName, String category,
                 LocalDateTime startsAt, String description, String priceLabel, List<String> tags,
                 String accentClass, int interestedCount) {
        this.id = id;
        this.title = title;
        this.venueId = venueId;
        this.venueName = venueName;
        this.category = category;
        this.startsAt = startsAt;
        this.description = description;
        this.priceLabel = priceLabel;
        this.tags = tags;
        this.accentClass = accentClass;
        this.interestedCount = interestedCount;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Long getVenueId() { return venueId; }
    public String getVenueName() { return venueName; }
    public String getCategory() { return category; }
    public LocalDateTime getStartsAt() { return startsAt; }
    public String getDescription() { return description; }
    public String getPriceLabel() { return priceLabel; }
    public List<String> getTags() { return tags; }
    public String getAccentClass() { return accentClass; }
    public int getInterestedCount() { return interestedCount; }
    public void incrementInterested() { this.interestedCount++; }
}
