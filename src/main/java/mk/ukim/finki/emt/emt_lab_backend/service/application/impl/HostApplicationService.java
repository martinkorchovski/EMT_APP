package mk.ukim.finki.emt.emt_lab_backend.service.application.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Host;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.HostCountryProjection;
import mk.ukim.finki.emt.emt_lab_backend.repository.CountryRepository;
import mk.ukim.finki.emt.emt_lab_backend.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationService implements mk.ukim.finki.emt.emt_lab_backend.service.application.HostApplicationService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostApplicationService(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public Optional<DisplayHostDTO> findById(Long id) {
        return hostRepository.findById(id).map(DisplayHostDTO::from);
    }

    @Override
    public DisplayHostDTO create(CreateHostDTO dto) {
        Country country = countryRepository.findById(dto.countryId()).orElseThrow(() -> new RuntimeException("Country not found"));


        Host host = new Host();

        host.setName(dto.name());
        host.setSurname(dto.surname());
        host.setCountry(country);

        Host savedHost = hostRepository.save(host);

        return DisplayHostDTO.from(savedHost);
    }

    @Override
    public Optional<DisplayHostDTO> update(Long id, CreateHostDTO dto) {
        return hostRepository.findById(id).map(host -> {
            Country country = countryRepository.findById(dto.countryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));

            host.setName(dto.name());
            host.setSurname(dto.surname());
            host.setCountry(country);

            Host saved = hostRepository.save(host);
            return DisplayHostDTO.from(saved);
        });
    }

    @Override
    public Optional<DisplayHostDTO> deleteById(Long id) {
        return hostRepository.findById(id).map(host -> {
            hostRepository.delete(host);
            return DisplayHostDTO.from(host);
        });
    }

    @Override
    public List<HostCountryProjection> getHostCountPerCountry() {
        return hostRepository.findHostCountPerCountry();
    }
}
