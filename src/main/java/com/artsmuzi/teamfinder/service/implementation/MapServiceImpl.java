package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.dto.FieldDto;
import com.artsmuzi.teamfinder.mapper.FieldMapper;
import com.artsmuzi.teamfinder.model.Field;
import com.artsmuzi.teamfinder.repository.FieldRepository;
import com.artsmuzi.teamfinder.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class MapServiceImpl implements MapService {

    private final FieldRepository repository;
    private final FieldMapper fieldMapper;
    private final Logger logger = LoggerFactory.getLogger(MapService.class);

    @Autowired
    public MapServiceImpl(FieldRepository repository, FieldMapper fieldMapper) {
        this.repository = repository;
        this.fieldMapper = fieldMapper;
    }

    @Override
    public List<FieldDto> getAllFields() {

        return repository
                .findAll().stream()
                .map(fieldMapper::toDto).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public FieldDto addField(FieldDto field) {
        Field entity = fieldMapper.toEntity(field);

        return fieldMapper.toDto(repository.save(entity) );
    }

    @Override
    public void deleteField(Long fieldId) {
       repository.findById(fieldId).ifPresent(repository::delete);
    }
}