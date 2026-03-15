package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDTO>> findAllAccommodations() {
        return ResponseEntity.ok(accommodationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDTO> findAccommodationByID(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDTO> createAccommodation(@RequestBody CreateAccommodationDTO createBookDTO) {
        return ResponseEntity.ok(accommodationService.create(createBookDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDTO> updateAccommodation(@PathVariable Long id,
                                                                       @RequestBody CreateAccommodationDTO createBookDTO) {
        return accommodationService
                .update(id, createBookDTO)
                .map(it -> ResponseEntity.ok().body(it))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return accommodationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/markAsRead/{id}")
    public ResponseEntity<Optional<DisplayAccommodationDTO>> rentBook(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.markAsRented(id));
    }


}
