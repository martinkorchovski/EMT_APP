package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import java.time.LocalDateTime;

public class DisplayReservationDTO{
    private Long id;
    private String accommodationName;
    private String username;
    private LocalDateTime reservedAt;
    private LocalDateTime releaseAt;

    public DisplayReservationDTO() {}

    public DisplayReservationDTO(Long id, String accommodationName, String username,
                                 LocalDateTime reservedAt, LocalDateTime releaseAt) {
        this.id = id;
        this.accommodationName = accommodationName;
        this.username = username;
        this.reservedAt = reservedAt;
        this.releaseAt = releaseAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccommodationName() { return accommodationName; }
    public void setAccommodationName(String accommodationName) { this.accommodationName = accommodationName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getReservedAt() { return reservedAt; }
    public void setReservedAt(LocalDateTime reservedAt) { this.reservedAt = reservedAt; }

    public LocalDateTime getReleaseAt() { return releaseAt; }
    public void setReleaseAt(LocalDateTime releaseAt) { this.releaseAt = releaseAt; }
}