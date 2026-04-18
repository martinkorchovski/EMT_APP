package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

    List<Accommodation> findByIsRentedTrue();

    List<Accommodation> findByIsRentedFalse();

}
