package com.artsmuzi.teamfinder.service;

import com.artsmuzi.teamfinder.dto.FieldDto;
import com.artsmuzi.teamfinder.model.Field;

import java.util.List;

public interface MapService {
    List<FieldDto> getAllFields();
    FieldDto addField(FieldDto request);
    void deleteField(Long fieldId);
}
