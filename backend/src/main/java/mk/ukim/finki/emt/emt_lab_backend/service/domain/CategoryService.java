package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}