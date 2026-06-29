package com.nocturne.app.service;

import com.nocturne.app.model.VibePost;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VibeService {

    private final List<VibePost> posts = new ArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public VibeService() {
        seed();
    }

    private void seed() {
        add("Maya R.", "Static Garden", "Floor is shoulder to shoulder and the bass just dropped. Unreal.",
                LocalDateTime.now().minusMinutes(6), 5, "accent-cyan", true);
        add("Theo K.", "The Velvet Hour", "Quiet crowd, perfect for a slow first drink before it fills up.",
                LocalDateTime.now().minusMinutes(22), 2, "accent-magenta", false);
        add("Priya N.", "Subline", "Sub-bass is rattling the railings upstairs. Bring earplugs if you're sensitive.",
                LocalDateTime.now().minusMinutes(41), 4, "accent-cyan", true);
        add("Diego F.", "Marrow", "Selector just dropped an obscure 1978 disco pressing, crowd went feral.",
                LocalDateTime.now().minusHours(1), 4, "accent-amber", false);
        add("Lena W.", "Glasshouse Rooftop", "Sun's almost down, line for the rooftop is moving fast.",
                LocalDateTime.now().minusHours(2), 3, "accent-violet", false);
    }

    private void add(String authorName, String venueName, String caption, LocalDateTime postedAt,
                      int heatLevel, String accentClass, boolean isLive) {
        long id = idSequence.getAndIncrement();
        posts.add(new VibePost(id, authorName, venueName, caption, postedAt, heatLevel, accentClass, isLive));
    }

    public List<VibePost> findRecent(int limit) {
        return posts.stream()
                .sorted(Comparator.comparing(VibePost::getPostedAt).reversed())
                .limit(limit)
                .toList();
    }

    public VibePost createPost(String authorName, String venueName, String caption, int heatLevel, boolean isLive) {
        long id = idSequence.getAndIncrement();
        String[] palette = {"accent-magenta", "accent-cyan", "accent-amber", "accent-violet"};
        String accent = palette[(int) (id % palette.length)];
        VibePost post = new VibePost(id, authorName, venueName, caption, LocalDateTime.now(), heatLevel, accent, isLive);
        posts.add(post);
        return post;
    }
}
