package mk.ukim.finki.emt.emt_lab_backend.model.listeners;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.AccommodationActivity;
import mk.ukim.finki.emt.emt_lab_backend.model.events.AccommodationRentedEvent;
import mk.ukim.finki.emt.emt_lab_backend.repository.AccommodationActivityRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AccommodationRentedEventListener {

    private final AccommodationActivityRepository activityRepository;

    public AccommodationRentedEventListener(AccommodationActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @EventListener
    public void onAccommodationRented(AccommodationRentedEvent event) {
        var accommodation = event.getAccommodation();

        System.out.println("Accommodation rented: " + accommodation.getName()
                + " | Rooms: " + accommodation.getNumRooms()
                + " | Host: " + accommodation.getHost().getName() + " " + accommodation.getHost().getSurname());

        if (accommodation.getNumRooms() == 0) {
            System.out.println("WARNING: Accommodation " + accommodation.getName() + " has no free rooms left!");
        }

        activityRepository.save(new AccommodationActivity(
                accommodation.getName(),
                LocalDateTime.now(),
                "RENTED"
        ));
    }
}