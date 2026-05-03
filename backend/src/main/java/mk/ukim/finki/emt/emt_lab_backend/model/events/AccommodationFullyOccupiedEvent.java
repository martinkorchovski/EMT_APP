package mk.ukim.finki.emt.emt_lab_backend.model.events;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationFullyOccupiedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationFullyOccupiedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
