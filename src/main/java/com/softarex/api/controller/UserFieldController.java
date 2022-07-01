package com.softarex.api.controller;

import com.softarex.api.entity.dto.*;
import com.softarex.api.service.UserFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users/{id}/fields")
public class UserFieldController {

    private final UserFieldService userFieldService;

    @Autowired
    public UserFieldController(UserFieldService userFieldService) {
        this.userFieldService = userFieldService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FieldIdDto post(@PathVariable("id") Long id, @RequestBody @Valid CreateFieldDto createFieldDto, Principal principal) {
        return userFieldService.add(createFieldDto, principal, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{fieldId}")
    public void delete(@PathVariable("id") Long id, @PathVariable("fieldId") Long fieldId, Principal principal) {
        userFieldService.delete(fieldId, id, principal);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{fieldId}")
    public void put(@PathVariable("id") Long id, @PathVariable("fieldId") Long fieldId, @RequestBody CreateFieldDto createFieldDto, Principal principal) {
        userFieldService.update(createFieldDto, principal, id, fieldId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public FieldPageDto getPage(@PathVariable("id") Long id, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {
        return userFieldService.getPage(pageable, id, principal);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public FieldListDto getAll(@PathVariable("id") Long id, Principal principal) {
        return userFieldService.getAll(id, principal);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{fieldId}")
    public FieldDto get(@PathVariable("id") Long id, @PathVariable("fieldId") Long fieldId, Principal principal) {
        return userFieldService.get(fieldId, id, principal);
    }
}
