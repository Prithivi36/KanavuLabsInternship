package com.incubator.Virtual.Incubator.Controller;

import com.incubator.Virtual.Incubator.Dto.StartupProfileDto;
import com.incubator.Virtual.Incubator.Service.StartupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StartupController {

    StartupService startupService;

    @PostMapping("/startup/{id}/{type}")
    public ResponseEntity<String> saveStartup(@PathVariable("id") Long id, @RequestBody StartupProfileDto startupProfileDto, @PathVariable("type") String type){
        return ResponseEntity.ok(startupService.saveStartup(id,startupProfileDto,type));
    }
}
