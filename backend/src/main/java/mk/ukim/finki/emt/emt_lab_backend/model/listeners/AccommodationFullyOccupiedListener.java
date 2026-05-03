package mk.ukim.finki.emt.emt_lab_backend.model.listeners;

import mk.ukim.finki.emt.emt_lab_backend.model.events.AccommodationFullyOccupiedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationFullyOccupiedListener {

    @EventListener
    public void onFullyOccupied(AccommodationFullyOccupiedEvent event) {
        var accommodation = event.getAccommodation();
        System.out.println("FULLY OCCUPIED: '" + accommodation.getName()
                + "' has no free rooms left! Time: " + java.time.LocalDateTime.now());
    }
}