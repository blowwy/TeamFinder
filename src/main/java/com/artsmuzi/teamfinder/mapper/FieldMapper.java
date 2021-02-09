package com.artsmuzi.teamfinder.mapper;

import com.artsmuzi.teamfinder.dto.FieldDto;
import com.artsmuzi.teamfinder.model.Field;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FieldMapper {
    @Autowired
    private ModelMapper mapper;

    public FieldDto toDto(Field field) {
        return Objects.isNull(field) ? null : mapper.map(field, FieldDto.class);
    }

    public Field toEntity(FieldDto fieldDto) {
        return Objects.isNull(fieldDto) ? null : mapper.map(fieldDto, Field.class);
    }
}
