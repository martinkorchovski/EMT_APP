package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.AccommodationActivity;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationStatsView;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationView;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.AccommodationResponseDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayAccommodationResponseDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationLongProjection;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationShortProjection;
import mk.ukim.finki.emt.emt_lab_backend.service.application.AccommodationApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public Page<AccommodationResponseDTO> findAllFiltered(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer rooms,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortBy,
            @RequestParam String direction
    ) {
        return accommodationService.findAll(
                name, categoryId, hostId, country, rooms,
                page, size, sortBy, direction
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDTO> findAccommodationByID(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paged")
    public Page<DisplayAccommodationResponseDTO> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationService.findAll(page, size);
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
    public ResponseEntity<?> deleteAccommodation(@PathVariable Long id) {
        return accommodationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDTO> rentAccommodation(@PathVariable Long id) {
        return accommodationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rented")
    public List<DisplayAccommodationDTO> getRentedAccommodations() {
        return accommodationService.findAllByRentedIsTrue();
    }

    @GetMapping("/notRented")
    public List<DisplayAccommodationDTO> getAvailableAccommodations() {
        return accommodationService.findAllByRentedIsFalse();
    }

    @GetMapping("/shortProjection")
    public List<AccommodationShortProjection> getShort() {
        return accommodationService.getShort();
    }

    @GetMapping("/longProjection")
    public List<AccommodationLongProjection> getDetailed() {
        return accommodationService.getDetailed();
    }

    @GetMapping("/withHostAndCountry")
    public ResponseEntity<List<Accommodation>> getAllWithHostAndCountry() {
        return ResponseEntity.ok(accommodationService.findAllWithHostAndCountry());
    }

    @GetMapping("/{id}/withHostAndCountry")
    public ResponseEntity<Accommodation> getByIdWithHostAndCountry(@PathVariable Long id) {
        return accommodationService.findWithHostAndCountryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/view")
    public ResponseEntity<List<AccommodationView>> getAllFromView() {
        return ResponseEntity.ok(accommodationService.findAllFromView());
    }

    @GetMapping("/stats")
    public ResponseEntity<List<AccommodationStatsView>> getStats() {
        return ResponseEntity.ok(accommodationService.findAllStats());
    }

    @GetMapping("/activities")
    public Page<AccommodationActivity> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationService.findAllActivities(page, size);
    }
}
