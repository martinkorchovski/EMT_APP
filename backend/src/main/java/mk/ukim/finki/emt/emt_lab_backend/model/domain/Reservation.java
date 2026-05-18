package mk.ukim.finki.emt.emt_lab_backend.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservedAt;

    private LocalDateTime releaseAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDateTime getReleaseAt() {
        return releaseAt;
    }

    public void setReleaseAt(LocalDateTime releaseAt) {
        this.releaseAt = releaseAt;
    }
}
