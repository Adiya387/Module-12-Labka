package module12Home2;

import java.time.LocalDate;
import java.util.List;

class Venue {
    private  String id;
    private  String name;
    private  int capacity;

    public Venue(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " (id=" + id + ", capacity=" + capacity + ")";
    }
}

class BookingRequest {
    private final LocalDate eventDate;
    private final int guestsCount;
    private final String preferences;

    public BookingRequest(LocalDate eventDate, int guestsCount, String preferences) {
        this.eventDate = eventDate;
        this.guestsCount = guestsCount;
        this.preferences = preferences;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public int getGuestsCount() {
        return guestsCount;
    }

    public String getPreferences() {
        return preferences;
    }
}

class Booking {
    private final String id;
    private final Venue venue;
    private final LocalDate date;
    private BookingStatus status;

    public Booking(String id, Venue venue, LocalDate date) {
        this.id = id;
        this.venue = venue;
        this.date = date;
        this.status = BookingStatus.CREATED;
    }

    public String getId() {
        return id;
    }

    public Venue getVenue() {
        return venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
