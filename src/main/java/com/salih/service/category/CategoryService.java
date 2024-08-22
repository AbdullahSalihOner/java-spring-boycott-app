package com.salih.service.category;

import com.salih.model.entity.Category;
import com.salih.repository.CategoryRepository;
import com.salih.service.ICrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICrudService<Category,Long> {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getById(Long aLong) {
        return null;
    }

    @Override
    public Category add(Category entity) {
        return null;
    }

    @Override
    public Category update(Long aLong, Category entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
