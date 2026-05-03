package mk.ukim.finki.emt.emt_lab_backend.service.application.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.*;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.*;
import mk.ukim.finki.emt.emt_lab_backend.model.events.AccommodationFullyOccupiedEvent;
import mk.ukim.finki.emt.emt_lab_backend.model.events.AccommodationRentedEvent;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationLongProjection;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationShortProjection;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationStatsView;
import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationView;
import mk.ukim.finki.emt.emt_lab_backend.repository.*;
import mk.ukim.finki.emt.emt_lab_backend.service.application.AccommodationApplicationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationRepository accommodationRepository;
    private final CategoryRepository categoryRepository;
    private final StateRepository stateRepository;
    private final HostRepository hostRepository;
    private final AccommodationViewRepository accommodationViewRepository;
    private final AccommodationStatsViewRepository accommodationStatsViewRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AccommodationActivityRepository accommodationActivityRepository;

    public AccommodationApplicationServiceImpl(AccommodationRepository accommodationRepository,
                                               CategoryRepository categoryRepository,
                                               StateRepository stateRepository,
                                               HostRepository hostRepository, AccommodationViewRepository accommodationViewRepository, AccommodationStatsViewRepository accommodationStatsViewRepository, ApplicationEventPublisher eventPublisher, AccommodationActivityRepository accommodationActivityRepository) {
        this.accommodationRepository = accommodationRepository;
        this.categoryRepository = categoryRepository;
        this.stateRepository = stateRepository;
        this.hostRepository = hostRepository;
        this.accommodationViewRepository = accommodationViewRepository;
        this.accommodationStatsViewRepository = accommodationStatsViewRepository;
        this.eventPublisher = eventPublisher;
        this.accommodationActivityRepository = accommodationActivityRepository;
    }

    private AccommodationResponseDTO toDTO(Accommodation a) {
        return new AccommodationResponseDTO(
                a.getId(),
                a.getName(),
                a.getNumRooms(),
                a.getCategory().getName(),
                a.getState().getName(),
                a.getHost().getName() + " " + a.getHost().getSurname(),
                a.getHost().getCountry().getName()
        );
    }

    @Override
    public Page<AccommodationResponseDTO> findAll(
            String name,
            Long categoryId,
            Long hostId,
            String country,
            Integer rooms,
            Integer page,
            Integer size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Accommodation> pageResult = accommodationRepository.findAllFiltered(
                name, categoryId, hostId, country, rooms, pageable
        );

        return pageResult.map(this::toDTO);
    }


    @Override
    public Optional<DisplayAccommodationDTO> findById(Long id) {
        return accommodationRepository.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public DisplayAccommodationDTO create(CreateAccommodationDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found with id: " + dto.categoryId()));

        State state = stateRepository.findById(dto.stateId())
                .orElseThrow(() -> new IllegalStateException("State not found with id: " + dto.stateId()));

        Host host = hostRepository.findById(dto.hostId())
                .orElseThrow(() -> new IllegalStateException("Host not found with id: " + dto.hostId()));


        Accommodation accommodation = new Accommodation(
                dto.name(),
                dto.numRooms(),
                category,
                state,
                host
        );

        Accommodation saved = accommodationRepository.save(accommodation);
        return DisplayAccommodationDTO.from(saved);
    }

    @Override
    public Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO dto) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    Category category = categoryRepository.findById(dto.categoryId())
                            .orElseThrow(() -> new IllegalStateException("Category not found with id: " + dto.categoryId()));

                    State state = stateRepository.findById(dto.stateId())
                            .orElseThrow(() -> new IllegalStateException("State not found with id: " + dto.stateId()));

                    Host host = hostRepository.findById(dto.hostId())
                            .orElseThrow(() -> new IllegalStateException("Host not found with id: " + dto.hostId()));

                    acc.setName(dto.name());
                    acc.setNumRooms(dto.numRooms());
                    acc.setCategory(category);
                    acc.setState(state);
                    acc.setHost(host);

                    Accommodation updated = accommodationRepository.save(acc);
                    return DisplayAccommodationDTO.from(updated);
                });
    }

    @Override
    public Optional<DisplayAccommodationDTO> deleteById(Long id) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    accommodationRepository.delete(acc);
                    return DisplayAccommodationDTO.from(acc);
                });

    }

    @Override
    public Optional<DisplayAccommodationDTO> markAsRented(Long id) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setRented(true);
                    Accommodation updated = accommodationRepository.save(acc);

                    eventPublisher.publishEvent(new AccommodationRentedEvent(this, updated));

                    if (updated.getRented()) {
                        eventPublisher.publishEvent(new AccommodationFullyOccupiedEvent(this, updated));
                    }

                    return DisplayAccommodationDTO.from(updated);
                });
    }

    @Override
    public List<DisplayAccommodationDTO> findAllByRentedIsFalse() {
        return accommodationRepository.findByIsRentedFalse()
                .stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }

    @Override
    public List<DisplayAccommodationDTO> findAllByRentedIsTrue() {
        return accommodationRepository.findByIsRentedTrue()
                .stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }

    @Override
    public Page<DisplayAccommodationResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return accommodationRepository.findAll(pageable)
                .map(DisplayAccommodationResponseDTO::from);
    }

    @Override
    public List<AccommodationShortProjection> getShort() {
        return accommodationRepository.findAllShort();
    }

    @Override
    public List<AccommodationLongProjection> getDetailed() {
        return accommodationRepository.findAllDetailed();
    }

    @Override
    public List<Accommodation> findAllWithHostAndCountry() {
        return accommodationRepository.findAllWithHostAndCountry();
    }

    @Override
    public Optional<Accommodation> findWithHostAndCountryById(Long id) {
        return accommodationRepository.findWithHostAndCountryById(id);
    }

    @Override
    public List<AccommodationView> findAllFromView() {
        return accommodationViewRepository.findAll();
    }

    @Override
    public List<AccommodationStatsView> findAllStats() {
        return accommodationStatsViewRepository.findAll();
    }

    @Override
    public Page<AccommodationActivity> findAllActivities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accommodationActivityRepository.findAll(pageable);
    }
}
