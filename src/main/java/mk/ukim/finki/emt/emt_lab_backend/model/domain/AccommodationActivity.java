package mk.ukim.finki.emt.emt_lab_backend.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "accommodation_activities")
public class AccommodationActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accommodationName;

    private LocalDateTime eventTime;

    private String eventType;

    public AccommodationActivity() {
    }

    public AccommodationActivity(String accommodationName, LocalDateTime eventTime, String eventType) {
        this.accommodationName = accommodationName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String getEventType() {
        return eventType;
    }
}