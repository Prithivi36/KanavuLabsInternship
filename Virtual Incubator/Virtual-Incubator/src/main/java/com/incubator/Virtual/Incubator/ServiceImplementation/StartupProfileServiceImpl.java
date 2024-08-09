package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.StartupProfileDto;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.StartupProfile;
import com.incubator.Virtual.Incubator.Exception.ExceptionDetail;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Repository.MentorRepository;
import com.incubator.Virtual.Incubator.Repository.StartupRepository;
import com.incubator.Virtual.Incubator.Service.StartupService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartupProfileServiceImpl implements StartupService {
    StartupRepository startupRepository;
    MentorRepository mentorRepository;
    ModelMapper modelMapper;
    AspirantRepository aspirantRepository;

    @Override
    public  String saveStartup(Long personId, StartupProfileDto startupProfileDto, String type) {

        StartupProfile startupProfile=startupRepository.save(modelMapper.map(startupProfileDto, StartupProfile.class));
        if(type.equals("mentor")){
            Mentor mentor=mentorRepository.findById(personId).orElseThrow(
                    ()-> new ExceptionDetail(HttpStatus.NOT_FOUND,"Mentor Not Found")
            );
            mentor.setStartupProfile(startupProfile);
            mentorRepository.save(mentor);
        } else if (type.equals("aspirant")) {
            Aspirant aspirant =aspirantRepository.findById(personId).orElseThrow(
                    ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant Not found")
            );
            aspirant.setStartupProfile(startupProfile);
            aspirantRepository.save(aspirant);
        }
        return "Successfully Saved";
    }
}
