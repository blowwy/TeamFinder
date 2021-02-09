package com.artsmuzi.teamfinder.controller;

import com.artsmuzi.teamfinder.dto.TeamDto;
import com.artsmuzi.teamfinder.model.Team;
import com.artsmuzi.teamfinder.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping(value = "/{id}/teams")
    public List<TeamDto> getTeams(@PathVariable("id") Long id) {
        return fieldService.getFieldTeams(id);
    }

    @PostMapping(value = "/{id}/team")
    public TeamDto addTeam(@PathVariable("id") Long id, @RequestBody TeamDto team) {
        return fieldService.addTeamToField(id, team);
    }

    @PostMapping(value = "/{fieldId}/team/{teamId}")
     public TeamDto addTeam(@PathVariable("fieldId") Long fieldId, @PathVariable("teamId") Long teamId) {
        return fieldService.addTeamToField(fieldId, teamId);
    }

    @DeleteMapping(value = "/{fieldId}/team/{teamId}")
    public TeamDto deleteTeam(@PathVariable("fieldId") Long fieldId, @PathVariable("teamId") Long teamId) {
        return fieldService.deleteTeamFromField(fieldId, teamId);
    }
}
