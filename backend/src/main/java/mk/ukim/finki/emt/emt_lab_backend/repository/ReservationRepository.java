package mk.ukim.finki.emt.emt_lab_backend.repository;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserId(Long userId);
    Optional<Reservation> findByAccommodationId(Long accommodationId);
}
