package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import java.time.LocalDateTime;

public class CreateReservationDTO {
    private Long accommodationId;
    private Long userId;
    private LocalDateTime releaseAt;

    public CreateReservationDTO() {}

    public Long getAccommodationId() { return accommodationId; }
    public void setAccommodationId(Long accommodationId) { this.accommodationId = accommodationId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getReleaseAt() { return releaseAt; }
    public void setReleaseAt(LocalDateTime releaseAt) { this.releaseAt = releaseAt; }
}