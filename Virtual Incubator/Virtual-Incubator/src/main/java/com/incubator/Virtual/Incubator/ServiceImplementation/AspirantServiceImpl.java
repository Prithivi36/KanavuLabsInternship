package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.RequestsDto;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.Requests;
import com.incubator.Virtual.Incubator.Exception.ExceptionDetail;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Repository.MentorRepository;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AspirantServiceImpl implements AspirantService {

    AspirantRepository aspirantRepository;
    ModelMapper modelMapper;
    MentorRepository mentorRepository;

    @Override
    public String saveAspirant(AspirantDto aspirantDto) {
        aspirantRepository.save(modelMapper.map(aspirantDto, Aspirant.class));
        return "Successfully saved aspirant";
    }

    @Override
    public AspirantDto getAspirant(Long id) {
        return modelMapper.map(aspirantRepository.findById(id), AspirantDto.class);
    }

    @Override
    public List<AspirantDto> getAllAspirants() {
        return aspirantRepository.findAll().stream().map((asp)->modelMapper.map(asp,AspirantDto.class)).toList();
    }

    public List<RequestsDto<Mentor>> viewMentorOffers(Long id){
        List<Requests> request= aspirantRepository.findById(id).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant not found"))
                .getRequests();

        return request.stream().map((rqst)->{
            RequestsDto<Mentor> modified=new RequestsDto<>();
            modified.setPerson(mentorRepository.findById(rqst.getMntId()).orElseThrow(
                    ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Mentor not found")));
            modified.setMessage(rqst.getMessage());
            modified.setDateTime(rqst.getDateTime());
            return modified;
        }).toList();
    }

}
