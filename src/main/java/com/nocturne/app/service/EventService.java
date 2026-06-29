package com.nocturne.app.service;

import com.nocturne.app.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventService {

    private final Map<Long, Event> events = new LinkedHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public EventService() {
        seed();
    }

    private void seed() {
        LocalDateTime now = LocalDateTime.now();

        add("Afterglow: Deep House Sessions", 1L, "The Velvet Hour", "Club Night",
                now.plusHours(5), "A late-night deep house residency with guest selectors flown in from the coast.",
                "Free before 11", List.of("house", "late-night"), "accent-magenta", 312);

        add("Concrete Pulse", 2L, "Static Garden", "Techno",
                now.plusDays(1).withHour(22).withMinute(0),
                "Industrial techno across two rooms, plus a visual installation in the warehouse loft.",
                "$18", List.of("techno", "warehouse"), "accent-cyan", 540);

        add("Vinyl Only: Funk & Disco Night", 3L, "Marrow", "Disco",
                now.plusDays(2).withHour(23).withMinute(0),
                "Strictly analog. Resident selectors dig through crates of original-pressing funk and disco.",
                "$12", List.of("disco", "funk", "vinyl"), "accent-amber", 198);

        add("Sunset Sessions on the Glass", 4L, "Glasshouse Rooftop", "Rooftop",
                now.plusDays(3).withHour(18).withMinute(30),
                "Golden-hour afrobeat and lounge sets with the whole skyline as backdrop.",
                "$15", List.of("afrobeat", "rooftop", "sunset"), "accent-violet", 267);

        add("Subline Bass Takeover", 5L, "Subline", "Drum & Bass",
                now.plusDays(4).withHour(21).withMinute(0),
                "A full night of jungle and drum & bass on a sound system built for sub-bass.",
                "$20", List.of("dnb", "bass", "underground"), "accent-cyan", 421);

        add("Midweek Wind-Down", 1L, "The Velvet Hour", "Lounge",
                now.plusDays(2).withHour(20).withMinute(0),
                "A slower, smokier midweek set for people who want the vibe without the marathon.",
                "Free", List.of("lounge", "midweek"), "accent-magenta", 84);
    }

    private void add(String title, Long venueId, String venueName, String category, LocalDateTime startsAt,
                      String description, String priceLabel, List<String> tags, String accentClass, int interestedCount) {
        long id = idSequence.getAndIncrement();
        events.put(id, new Event(id, title, venueId, venueName, category, startsAt, description,
                priceLabel, tags, accentClass, interestedCount));
    }

    public List<Event> findAll() {
        return events.values().stream()
                .sorted((a, b) -> a.getStartsAt().compareTo(b.getStartsAt()))
                .toList();
    }

    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(events.get(id));
    }

    public List<Event> findByVenue(Long venueId) {
        return events.values().stream()
                .filter(e -> e.getVenueId().equals(venueId))
                .toList();
    }

    public List<Event> findUpcoming(int limit) {
        return findAll().stream().limit(limit).toList();
    }

    public void markInterested(Long eventId) {
        Event event = events.get(eventId);
        if (event != null) {
            event.incrementInterested();
        }
    }
}
