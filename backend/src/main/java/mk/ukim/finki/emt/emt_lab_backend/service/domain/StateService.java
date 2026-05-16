package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.State;
import java.util.List;

public interface StateService {
    List<State> findAll();
}