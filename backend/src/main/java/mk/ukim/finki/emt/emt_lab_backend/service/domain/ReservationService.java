package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation reserve(Long accommodationId, Long userId, LocalDateTime releaseAt);
}
