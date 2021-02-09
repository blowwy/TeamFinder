package com.artsmuzi.teamfinder.controller;

import com.artsmuzi.teamfinder.dto.FieldDto;
import com.artsmuzi.teamfinder.model.Field;
import com.artsmuzi.teamfinder.service.implementation.MapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/map")
public class MapController {

    @Autowired
    private MapServiceImpl mapService;

    @GetMapping(value = "/fields")
    public List<FieldDto> getAllFields() {
        return mapService.getAllFields();
    }

    @PostMapping(value = "/field")
    public FieldDto addField(@RequestBody FieldDto fieldDto) { return mapService.addField(fieldDto); }

    @DeleteMapping(value = "/field/{id}")
    public void deleteField(@PathVariable Long id) { mapService.deleteField(id); }

}
