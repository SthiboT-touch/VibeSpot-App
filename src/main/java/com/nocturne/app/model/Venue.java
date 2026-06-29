package com.nocturne.app.model;

import java.util.List;

public class Venue {

    private final Long id;
    private final String name;
    private final String neighborhood;
    private final String address;
    private final String description;
    private final List<String> genres;
    private final String capacityLabel;
    private final boolean openNow;
    private int vibeScore; // 0-100, how lit the venue is right now
    private final String accentClass; // css gradient/accent token for placeholder art

    public Venue(Long id, String name, String neighborhood, String address, String description,
                 List<String> genres, String capacityLabel, boolean openNow, int vibeScore, String accentClass) {
        this.id = id;
        this.name = name;
        this.neighborhood = neighborhood;
        this.address = address;
        this.description = description;
        this.genres = genres;
        this.capacityLabel = capacityLabel;
        this.openNow = openNow;
        this.vibeScore = vibeScore;
        this.accentClass = accentClass;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getNeighborhood() { return neighborhood; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
    public List<String> getGenres() { return genres; }
    public String getCapacityLabel() { return capacityLabel; }
    public boolean isOpenNow() { return openNow; }
    public int getVibeScore() { return vibeScore; }
    public void setVibeScore(int vibeScore) { this.vibeScore = vibeScore; }
    public String getAccentClass() { return accentClass; }
}
