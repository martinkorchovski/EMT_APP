package mk.ukim.finki.emt.emt_lab_backend.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "accommodations")
@NamedEntityGraph(
        name = "Accommodation.withHostAndCountry",
        attributeNodes = {
                @NamedAttributeNode(value = "host", subgraph = "host-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "host-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)
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

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Column(nullable = false)
    private Boolean isRented;

    public Accommodation() {
    }

    public Accommodation(String name, Integer numRooms, Category category, State state, Host host) {
        this.name = name;
        this.numRooms = numRooms;
        this.category = category;
        this.state = state;
        this.host = host;
        this.isRented = false;
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

    public Host getHost() {
        return host;
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

    public void setHost(Host host) {
        this.host = host;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }
}
