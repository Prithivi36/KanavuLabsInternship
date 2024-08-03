package com.incubator.Virtual.Incubator.Controller;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AspirantController {

    AspirantService aspirantService;

    @PostMapping("/asp/new")
    public ResponseEntity<String> saveAspirant(@RequestBody AspirantDto aspirantDto) {
        return new ResponseEntity<>(aspirantService.saveAspirant(aspirantDto), HttpStatus.CREATED);
    }

    @GetMapping("/asp/get/{id}")
    public ResponseEntity<AspirantDto> getAspirant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(aspirantService.getAspirant(id));
    }
    @GetMapping("/asp/all")
    public ResponseEntity<List<AspirantDto>> getAllAspirants() {
        return ResponseEntity.ok(aspirantService.getAllAspirants());
    }
}
