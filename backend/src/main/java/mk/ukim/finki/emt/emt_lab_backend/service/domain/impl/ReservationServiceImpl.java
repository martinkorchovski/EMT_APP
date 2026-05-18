package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Reservation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;
import mk.ukim.finki.emt.emt_lab_backend.repository.AccommodationRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.ReservationRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.UserRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AccommodationRepository accommodationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation reserve(Long accommodationId, Long userId, LocalDateTime releaseAt) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(() -> new RuntimeException("Accommodation not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (accommodation.getHost().getId().equals(userId)) {
            throw new RuntimeException("Host can not reserve its own accommodation");
        }

        Reservation reservation = new Reservation();
        reservation.setAccommodation(accommodation);
        reservation.setUser(user);
        reservation.setReleaseAt(releaseAt);
        reservation.setReservedAt(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }
}
