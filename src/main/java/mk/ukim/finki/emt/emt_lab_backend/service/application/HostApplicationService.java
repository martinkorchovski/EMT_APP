package mk.ukim.finki.emt.emt_lab_backend.service.application;

import mk.ukim.finki.emt.emt_lab_backend.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.HostCountryProjection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostApplicationService {
    Optional<DisplayHostDTO> findById(Long id);

    DisplayHostDTO create(CreateHostDTO dto);

    Optional<DisplayHostDTO> update(Long id, CreateHostDTO dto);

    Optional<DisplayHostDTO> deleteById(Long id);

    List<HostCountryProjection> getHostCountPerCountry();
}
