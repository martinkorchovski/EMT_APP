package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.repository.AccommodationRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setName(accommodation.getName());
                    acc.setNumRooms(accommodation.getNumRooms());
                    acc.setCategory(accommodation.getCategory());
                    acc.setState(accommodation.getState());
                    acc.setHost(accommodation.getHost());

                    return accommodationRepository.save(acc);
                });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            if (accommodation.getState().getName().equals("BAD")) {
                accommodationRepository.delete(accommodation);
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
        return accommodationRepository.findById(id)
                .map(acc -> {
                    acc.setRented(true);
                    return accommodationRepository.save(acc);
                });
    }

    @Override
    public List<Accommodation> findByIsRentedTrue() {
        return accommodationRepository.findByIsRentedTrue();
    }

    @Override
    public List<Accommodation> findByIsRentedFalse() {
        return accommodationRepository.findByIsRentedFalse();
    }
}
