package com.nocturne.app.service;

import com.nocturne.app.model.Venue;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VenueService {

    private final Map<Long, Venue> venues = new LinkedHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public VenueService() {
        seed();
    }

    private void seed() {
        add("The Velvet Hour", "Riverside District", "12 Wharf Lane",
                "A low-lit speakeasy with deep house sets and a copper-topped bar that never stops moving.",
                List.of("House", "Deep House"), "220 cap", true, 88, "accent-magenta");
        add("Static Garden", "Old Mill Quarter", "47 Foundry Street",
                "An outdoor-indoor hybrid venue built into a converted warehouse, known for techno and visual art.",
                List.of("Techno", "Electro"), "500 cap", true, 95, "accent-cyan");
        add("Marrow", "Harbor Lights", "3 Pier Walk",
                "Intimate basement club with a vinyl-only policy on weekends and a strict no-phones dance floor.",
                List.of("Disco", "Funk"), "150 cap", true, 76, "accent-amber");
        add("Glasshouse Rooftop", "Uptown Crescent", "88 Crescent Tower",
                "Skyline views, a curated cocktail list, and a resident DJ playing sunset-to-midnight sets.",
                List.of("Afrobeat", "Lounge"), "300 cap", false, 41, "accent-violet");
        add("Subline", "Old Mill Quarter", "9 Tunnel Row",
                "A converted transit tunnel turned bass-heavy dance floor with a sound system built for drum & bass.",
                List.of("Drum & Bass", "Jungle"), "400 cap", true, 91, "accent-cyan");
    }

    private void add(String name, String neighborhood, String address, String description,
                      List<String> genres, String capacityLabel, boolean openNow, int vibeScore, String accentClass) {
        long id = idSequence.getAndIncrement();
        venues.put(id, new Venue(id, name, neighborhood, address, description, genres,
                capacityLabel, openNow, vibeScore, accentClass));
    }

    public List<Venue> findAll() {
        return List.copyOf(venues.values());
    }

    public Optional<Venue> findById(Long id) {
        return Optional.ofNullable(venues.get(id));
    }

    public List<Venue> findTopVibing(int limit) {
        return venues.values().stream()
                .sorted((a, b) -> Integer.compare(b.getVibeScore(), a.getVibeScore()))
                .limit(limit)
                .toList();
    }
}
