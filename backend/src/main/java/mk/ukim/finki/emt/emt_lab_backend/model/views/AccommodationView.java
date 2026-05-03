package mk.ukim.finki.emt.emt_lab_backend.model.views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "accommodation_view")
public class AccommodationView {

    @Id
    private Long id;

    private String name;

    private String category;

    @Column(name = "numrooms")
    private Integer numRooms;

    @Column(name = "hostfullname")
    private String hostFullName;

    private String country;

    public AccommodationView() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public String getHostFullName() {
        return hostFullName;
    }

    public String getCountry() {
        return country;
    }
}