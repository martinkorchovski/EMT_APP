package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.repository.AccomodationRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccomodationRepository accomodationRepository;

    public AccommodationServiceImpl(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accomodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accomodationRepository.findById(id);
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return accomodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accomodationRepository.findById(id)
                .map(acc -> {
                    acc.setName(accommodation.getName());
                    acc.setNumRooms(accommodation.getNumRooms());
                    acc.setCategory(accommodation.getCategory());
                    acc.setState(accommodation.getState());
                    acc.setHosts(accommodation.getHosts());

                    return accomodationRepository.save(acc);
                });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        return accomodationRepository.findById(id).map(accommodation -> {
            if (accommodation.getState().getName().equals("BAD")) {
                accomodationRepository.delete(accommodation);
                return accommodation;
            } else {
                throw new IllegalStateException(
                        "The accommodation cannot be deleted because its condition is still "
                                + accommodation.getState().getName()
                );
            }
        });
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accomodationRepository.findById(id).map(accommodation -> {
            if (accommodation.getNumRooms() > 0) {
                accommodation.setNumRooms(accommodation.getNumRooms() - 1);
                return accomodationRepository.save(accommodation);
            } else {
                throw new IllegalStateException(
                        "The accommodation cannot be rented because there are no available rooms."
                );
            }
        });
    }
}
