package mk.ukim.finki.emt.emt_lab_backend.service.application.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Category;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Host;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.State;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.emt_lab_backend.repository.AccomodationRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.CategoryRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.HostRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.StateRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.application.AccommodationApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccomodationRepository accomodationRepository;
    private final CategoryRepository categoryRepository;
    private final StateRepository stateRepository;
    private final HostRepository hostRepository;

    public AccommodationApplicationServiceImpl(AccomodationRepository accomodationRepository,
                                               CategoryRepository categoryRepository,
                                               StateRepository stateRepository,
                                               HostRepository hostRepository) {
        this.accomodationRepository = accomodationRepository;
        this.categoryRepository = categoryRepository;
        this.stateRepository = stateRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<DisplayAccommodationDTO> findAll() {
        return accomodationRepository.findAll()
                .stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }


    @Override
    public Optional<DisplayAccommodationDTO> findById(Long id) {
        return accomodationRepository.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public DisplayAccommodationDTO create(CreateAccommodationDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found with id: " + dto.categoryId()));

        State state = stateRepository.findById(dto.stateId())
                .orElseThrow(() -> new IllegalStateException("State not found with id: " + dto.stateId()));

        List<Host> hosts = hostRepository.findAllById(dto.hostIds());


        Accommodation accommodation = new Accommodation(
                dto.name(),
                dto.numRooms(),
                category,
                state,
                hosts
        );

        Accommodation saved = accomodationRepository.save(accommodation);
        return DisplayAccommodationDTO.from(saved);
    }

    @Override
    public Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO dto) {
        return accomodationRepository.findById(id)
                .map(acc -> {
                    Category category = categoryRepository.findById(dto.categoryId())
                            .orElseThrow(() -> new IllegalStateException("Category not found with id: " + dto.categoryId()));

                    State state = stateRepository.findById(dto.stateId())
                            .orElseThrow(() -> new IllegalStateException("State not found with id: " + dto.stateId()));

                    List<Host> hosts = hostRepository.findAllById(dto.hostIds());
                    if (hosts.isEmpty()) {
                        throw new IllegalStateException("No hosts found for ids: " + dto.hostIds());
                    }

                    acc.setName(dto.name());
                    acc.setNumRooms(dto.numRooms());
                    acc.setCategory(category);
                    acc.setState(state);
                    acc.setHosts(hosts);

                    Accommodation updated = accomodationRepository.save(acc);
                    return DisplayAccommodationDTO.from(updated);
                });
    }

    @Override
    public Optional<DisplayAccommodationDTO> deleteById(Long id) {
        return accomodationRepository.findById(id)
                .map(acc -> {
                    accomodationRepository.delete(acc);
                    return DisplayAccommodationDTO.from(acc);
                });

    }

    @Override
    public Optional<DisplayAccommodationDTO> markAsRented(Long id) {
        return accomodationRepository.findById(id)
                .map(acc -> {
                    if (acc.getNumRooms() != null && acc.getNumRooms() > 0) {
                        acc.setNumRooms(acc.getNumRooms() - 1);
                    } else {
                        throw new IllegalStateException("No available rooms to rent for accommodation with id: " + id);
                    }

                    Accommodation updated = accomodationRepository.save(acc);
                    return DisplayAccommodationDTO.from(updated);
                });
    }
}
