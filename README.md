# VibeSpot-App


A nightlife discovery app: browse tonight's events, check out venues by how
"lit" they currently are, post a live vibe check-in, and manage a profile.
Built with Spring Boot (Java) + Thymeleaf, with all data held in memory —
no database setup required.

This is an original project, written from scratch as a Java/Spring Boot app.
It was inspired by the general concept of nightlife-discovery apps, but the
code, design, copy, and data model here are all new — not copied from any
existing site.

## Tech stack

- Java 17
- Spring Boot 3 (Web + Thymeleaf)
- Plain CSS (no frontend framework, no build step)
- In-memory data — everything resets when you restart the app

## Running it in IntelliJ IDEA

1. **Open the project**: `File > Open...` and select the `nocturne-app` folder
   (the one containing `pom.xml`). IntelliJ will detect it as a Maven project
   and download dependencies automatically (needs an internet connection the
   first time).
2. **Check the JDK**: `File > Project Structure > Project` and make sure the
   SDK is Java 17 or newer. If you don't have one, IntelliJ can download one
   for you from that same dialog.
3. **Run it**: open `src/main/java/com/nocturne/app/NocturneApplication.java`
   and click the green ▶ run icon next to the `main` method (or right-click
   the file and choose `Run`).
4. Once it says `Started NocturneApplication`, open
   **http://localhost:8080** in your browser.

You can also run it from a terminal in the project root:

```bash
./mvnw spring-boot:run
```

(If there's no Maven wrapper in your copy, use `mvn spring-boot:run` with a
local Maven install instead.)

## What's in the box

| Page | Route |
|---|---|
| Explore (home) | `/` |
| Events list | `/events` |
| Event detail | `/events/{id}` |
| Venue detail | `/venues/{id}` |
| Sign up | `/register` |
| Log in | `/login` |
| Forgot / reset password | `/forgot-password`, `/reset-password` |
| Profile | `/profile` |
| Post a vibe | `/post-vibe` |
| Go live | `/go-live` |

## Notes on the demo auth

Accounts are stored in memory and passwords are hashed with SHA-256 purely
to keep the demo dependency-free. That's fine for running locally and
learning from, but swap in something like Spring Security's
`BCryptPasswordEncoder` (and a real database) before this goes anywhere near
production or real user data.

## Where to make it your own

- `EventService`, `VenueService`, `VibeService` — edit the seed data to use
  your own events/venues/copy.
- `static/css/style.css` — all colors, type, and spacing live here as a
  small set of CSS variables at the top of the file.
- `templates/` — one Thymeleaf template per page, plus a shared
  `fragments/layout.html` for the nav bar and footer.
