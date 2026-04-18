package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Category;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Host;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.State;

import java.util.List;

public record CreateAccommodationDTO(
        @NotBlank(message = "Please provide the name of the accommodation!")
        String name,

        @Positive(message = "Please enter the number of rooms!")
        Integer numRooms,

        @NotNull(message = "Please select a category for the accommodation!")
        Long categoryId,

        @NotNull(message = "Please select the state of the accommodation!")
        Long stateId,

        @NotNull(message = "Please select one host for this accommodation!")
        Long hostId
) {
        public Accommodation toAccommodation(Category category, State state, Host host) {
                return new Accommodation(name, numRooms, category, state, host);
        }

}
