package mk.ukim.finki.emt.emt_lab_backend.service.application;

import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayAccommodationDTO;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDTO> findAll();

    Optional<DisplayAccommodationDTO> findById(Long id);

    DisplayAccommodationDTO create(CreateAccommodationDTO dto);

    Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO dto);

    Optional<DisplayAccommodationDTO> deleteById(Long id);

    Optional<DisplayAccommodationDTO> markAsRented(Long id);
}
