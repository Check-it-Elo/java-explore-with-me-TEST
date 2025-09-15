package ru.practicum.ewm.main.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main.dto.CategoryDto;
import ru.practicum.ewm.main.dto.NewCategoryDto;
import ru.practicum.ewm.main.service.CategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
public class AdminCategoriesController {

    private final CategoryService categoryService;

//    @PostMapping
//    public CategoryDto create(@RequestBody
//@Valid NewCategoryDto dto) {
//        return categoryService.create(dto);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@Valid @RequestBody NewCategoryDto dto) {
        return categoryService.create(dto);
    }

    @PatchMapping("/{catId}")
    public CategoryDto update(@PathVariable long catId,
                              @RequestBody
@Valid CategoryDto dto) {
        return categoryService.update(catId, dto);
    }

//    @DeleteMapping("/{catId}")
//    public void delete(@PathVariable
//@Min(1) long catId) {
//        categoryService.delete(catId);
//    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long catId) {
        categoryService.delete(catId);
    }
}