package mk.ukim.finki.emt.emt_lab_backend.model.events;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationRentedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationRentedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
