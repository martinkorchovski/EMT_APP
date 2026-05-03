package mk.ukim.finki.emt.emt_lab_backend.service.application;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.AccommodationActivity;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationStatsView;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationView;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.*;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationLongProjection;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
//    List<DisplayAccommodationDTO> findAll();

    Optional<DisplayAccommodationDTO> findById(Long id);

    DisplayAccommodationDTO create(CreateAccommodationDTO dto);

    Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO dto);

    Optional<DisplayAccommodationDTO> deleteById(Long id);

    Optional<DisplayAccommodationDTO> markAsRented(Long id);

    List<DisplayAccommodationDTO> findAllByRentedIsFalse();

    List<DisplayAccommodationDTO> findAllByRentedIsTrue();

    Page<DisplayAccommodationResponseDTO> findAll(int page, int size);

    Page<AccommodationResponseDTO> findAll(
            String name,
            Long categoryId,
            Long hostId,
            String country,
            Integer rooms,
            Integer page,
            Integer size,
            String sortBy,
            String direction
    );

    List<AccommodationShortProjection> getShort();

    List<AccommodationLongProjection> getDetailed();

    List<Accommodation> findAllWithHostAndCountry();

    Optional<Accommodation> findWithHostAndCountryById(Long id);

    List<AccommodationView> findAllFromView();

    List<AccommodationStatsView> findAllStats();

    Page<AccommodationActivity> findAllActivities(int page, int size);
}
