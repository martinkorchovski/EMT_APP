package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.BaseEntity;

import java.util.List;

public record DisplayAccommodationDTO(
        String name,
        Integer numRooms,
        Long categoryId,
        Long stateId,
        List<Long> hostsIds
) {
    public static DisplayAccommodationDTO from(Accommodation accomodation) {
        return new DisplayAccommodationDTO(
                accomodation.getName(),
                accomodation.getNumRooms(),
                accomodation.getCategory().getId(),
                accomodation.getState().getId(),
                accomodation.getHosts().stream().map(BaseEntity::getId).toList()
        );
    }

    public static List<DisplayAccommodationDTO> from(List<Accommodation> accommodations) {
        return accommodations
                .stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }
}
