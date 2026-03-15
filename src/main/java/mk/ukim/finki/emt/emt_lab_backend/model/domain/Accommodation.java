package mk.ukim.finki.emt.emt_lab_backend.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accommodations")
public class Accommodation extends BaseAuditableEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numRooms;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accommodations_hosts",
            joinColumns = @JoinColumn(name = "accommodation_id"),
            inverseJoinColumns = @JoinColumn(name = "host_id")
    )
    private List<Host> hosts;

    public Accommodation() {
    }

    public Accommodation(String name, Integer numRooms, Category category, State state, List<Host> hosts) {
        this.name = name;
        this.numRooms = numRooms;
        this.category = category;
        this.state = state;
        this.hosts = hosts;
    }

    public String getName() {
        return name;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public Category getCategory() {
        return category;
    }

    public State getState() {
        return state;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
