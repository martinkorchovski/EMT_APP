package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Reservation;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateReservationDTO;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestBody CreateReservationDTO createReservationDTO) {
        Reservation reservation = reservationService.reserve(
                createReservationDTO.getAccommodationId(),
                createReservationDTO.getUserId(),
                createReservationDTO.getReleaseAt()
        );
        return ResponseEntity.ok(reservation);
    }
}
