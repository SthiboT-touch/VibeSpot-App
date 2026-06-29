package com.nocturne.app.model;

import java.time.LocalDateTime;

public class VibePost {

    private final Long id;
    private final String authorName;
    private final String venueName;
    private final String caption;
    private final LocalDateTime postedAt;
    private final int heatLevel; // 1-5
    private final String accentClass;
    private final boolean isLive;

    public VibePost(Long id, String authorName, String venueName, String caption,
                     LocalDateTime postedAt, int heatLevel, String accentClass, boolean isLive) {
        this.id = id;
        this.authorName = authorName;
        this.venueName = venueName;
        this.caption = caption;
        this.postedAt = postedAt;
        this.heatLevel = heatLevel;
        this.accentClass = accentClass;
        this.isLive = isLive;
    }

    public Long getId() { return id; }
    public String getAuthorName() { return authorName; }
    public String getVenueName() { return venueName; }
    public String getCaption() { return caption; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public int getHeatLevel() { return heatLevel; }
    public String getAccentClass() { return accentClass; }
    public boolean isLive() { return isLive; }
}
