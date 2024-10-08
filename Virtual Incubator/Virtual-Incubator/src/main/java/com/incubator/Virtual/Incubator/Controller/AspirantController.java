package com.incubator.Virtual.Incubator.Controller;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.RequestsDto;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/asp")
public class AspirantController {

    AspirantService aspirantService;
    
    AspirantRepository aspirantRepository;

    @PostMapping("/new")
    public ResponseEntity<String> saveAspirant(@RequestBody AspirantDto aspirantDto) {
        
        return new ResponseEntity<>(aspirantService.saveAspirant(aspirantDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AspirantDto> getAspirant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(aspirantService.getAspirant(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<AspirantDto>> getAllAspirants() {
        return ResponseEntity.ok(aspirantService.getAllAspirants());
    }

    @GetMapping("/rqst/get/{id}")
    public ResponseEntity<List<RequestsDto<Mentor>>> getAllRequests(@PathVariable("id") Long id) {
        return ResponseEntity.ok(aspirantService.viewMentorOffers(id));
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<String> acceptMentorOffers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(aspirantService.acceptMentorOffer(id));
    }

    @PostMapping("/rqst/mnt/{mntId}/asp/{aspId}")
    public ResponseEntity<String> requestMentorShip(@PathVariable("mntId")Long mntId,@PathVariable("aspId")Long aspId){
        return ResponseEntity.ok(aspirantService.requestMentorship(mntId, aspId));
    }
}
