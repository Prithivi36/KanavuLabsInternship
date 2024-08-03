package com.incubator.Virtual.Incubator.Controller;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.MentorDto;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import com.incubator.Virtual.Incubator.Service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MentorController {
    MentorService mentorService;

    @PostMapping("/mnt/new")
    public ResponseEntity<String> saveAspirant(@RequestBody MentorDto mentorDto) {
        return new ResponseEntity<>(mentorService.saveMentor(mentorDto), HttpStatus.CREATED);
    }

    @GetMapping("/mnt/get/{id}")
    public ResponseEntity<MentorDto> getAspirant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mentorService.getMentor(id));
    }
    @GetMapping("/mnt/all")
    public ResponseEntity<List<MentorDto>> getAllAspirants() {
        return ResponseEntity.ok(mentorService.getAllMentors());
    }
    @PostMapping("mnt/offer/{asp}/{mnt}")
    public ResponseEntity<String> offerMentorShip(@PathVariable("asp") Long aspId,@PathVariable("mnt") Long mntId){
        return ResponseEntity.ok(mentorService.mentorOffer(aspId, mntId));
    }
}
