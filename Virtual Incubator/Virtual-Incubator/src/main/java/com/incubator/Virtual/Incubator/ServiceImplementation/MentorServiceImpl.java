package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.MentorDto;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.Requests;
import com.incubator.Virtual.Incubator.Exception.ExceptionDetail;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Repository.MentorRepository;
import com.incubator.Virtual.Incubator.Repository.RequestRepository;
import com.incubator.Virtual.Incubator.Service.MentorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {
    ModelMapper modelMapper;
    MentorRepository mentorRepository;
    AspirantRepository aspirantRepository;
    RequestRepository requestRepository;
    @Override
    public String saveMentor(MentorDto mentorDto) {
        mentorRepository.save(modelMapper.map(mentorDto, Mentor.class));
        return "Successfully saved Mentor";
    }

    @Override
    public MentorDto getMentor(Long id) {
        return modelMapper.map(mentorRepository.findById(id), MentorDto.class);
    }

    @Override
    public List<MentorDto> getAllMentors() {
        return mentorRepository.findAll().stream().map((ment)->modelMapper.map(ment,MentorDto.class)).toList();
    }

    public String mentorOffer(Long aspId,Long mntId){
        Aspirant aspirant=aspirantRepository.findById(aspId).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"User with id does not exist"));

        Requests request=requestRepository.findByAspIdAndMntId(aspId,mntId);
        if(request!=null){
            throw new ExceptionDetail(HttpStatus.CREATED,"Already Requested");
        }else {
            Requests requests = new Requests();
            requests.setMntId(mntId);
            requests.setMessage("Wants to be your mentor");
            requests.setAspId(aspId);
            requests.setStatus(false);
            requests.setDateTime(LocalDateTime.now());
            requestRepository.save(requests);
            aspirant.getRequests().add(requests);
            aspirantRepository.save(aspirant);
            return "Successfully sent Request";
        }
    }
}
