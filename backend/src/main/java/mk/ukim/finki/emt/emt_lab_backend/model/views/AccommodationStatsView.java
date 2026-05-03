package mk.ukim.finki.emt.emt_lab_backend.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "accommodation_stats_view")
public class AccommodationStatsView {

    @Id
    private String category;

    @Column(name = "totalaccommodations")
    private Long totalAccommodations;

    @Column(name = "totalrooms")
    private Long totalRooms;

    @Column(name = "avgrooms")
    private Double avgRooms;

    public AccommodationStatsView() {
    }

    public String getCategory() {
        return category;
    }

    public Long getTotalAccommodations() {
        return totalAccommodations;
    }

    public Long getTotalRooms() {
        return totalRooms;
    }

    public Double getAvgRooms() {
        return avgRooms;
    }
}
