package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.RequestsDto;
import com.incubator.Virtual.Incubator.Entity.AcceptedRequest;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.Requests;
import com.incubator.Virtual.Incubator.Exception.ExceptionDetail;
import com.incubator.Virtual.Incubator.Repository.AcceptedRequestsRepository;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Repository.MentorRepository;
import com.incubator.Virtual.Incubator.Repository.RequestRepository;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AspirantServiceImpl implements AspirantService {

    AspirantRepository aspirantRepository;
    ModelMapper modelMapper;
    MentorRepository mentorRepository;
    RequestRepository requestRepository;
    AcceptedRequestsRepository acceptedRequestsRepository;

    @Override
    public String saveAspirant(AspirantDto aspirantDto) {
        aspirantRepository.save(modelMapper.map(aspirantDto, Aspirant.class));
        return "Successfully saved aspirant";
    }

    @Override
    public AspirantDto getAspirant(Long id) {
        Aspirant aspirant=aspirantRepository.findById(id).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.BAD_REQUEST,"Not Found - Aspirant")
        );
        return modelMapper.map(aspirant, AspirantDto.class);
    }

    @Override
    public List<AspirantDto> getAllAspirants() {
        return aspirantRepository.findAll().stream().map(
                (asp)->modelMapper.map(asp,AspirantDto.class)).toList();
    }

    public List<RequestsDto<Mentor>> viewMentorOffers(Long id){
        List<Requests> request= aspirantRepository.findById(id).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant not found"))
                .getRequests();

        List<RequestsDto<Mentor>> rqstDto= request.stream().map((rqst)->{
            RequestsDto<Mentor> modified=new RequestsDto<>();
            modified.setPerson(mentorRepository.findById(rqst.getMntId()).orElseThrow(
                    ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Mentor not found")));
            modified.setMessage(rqst.getMessage());
            modified.setDateTime(rqst.getDateTime());
            modified.setRqstId(rqst.getId());
            return modified;
        }).toList();
        return rqstDto;
    }

    public String acceptMentorOffer(Long id){
        Requests rqst=requestRepository.findById(id).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Mentor request not found"));
        Aspirant asp=aspirantRepository.findById(rqst.getAspId()).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant not found"));
        Mentor mnt=mentorRepository.findById(rqst.getMntId()).orElseThrow(
                ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Mentor not found"));

        asp.getMentors().add(mnt);
        mnt.getAspirants().add(asp);
        rqst.setStatus(true);

        System.out.println("Deleted");
        AcceptedRequest acprqst=modelMapper.map(rqst,AcceptedRequest.class);
        acceptedRequestsRepository.save(acprqst);
        mentorRepository.save(mnt);
        aspirantRepository.save(asp);
        requestRepository.save(rqst);

        requestRepository.deleteById(id);

        return "Successfully accepted mentor";
    }

    public String requestMentorship(Long mntId,Long aspId) {
        Mentor mentor = mentorRepository.findById(mntId).orElseThrow(
                () -> new ExceptionDetail(HttpStatus.NOT_FOUND, "Mentor Id is wrong")
        );
        Aspirant aspirant = aspirantRepository.findById(aspId).orElseThrow(
                () -> new ExceptionDetail(HttpStatus.NOT_FOUND, "Aspirant Id is wrong")
        );

        if (requestRepository.findByAspIdAndMntId(aspId,mntId).isPresent() || acceptedRequestsRepository.findByAspIdAndMntId(aspId,mntId).isPresent()) {
            throw new ExceptionDetail(HttpStatus.CREATED, "Already You Have Receieved or Sent Request ");
        } else {
            Requests requests = new Requests();
            requests.setMntId(mntId);
            requests.setMessage("Wants you to be their mentor");
            requests.setAspId(aspId);
            requests.setStatus(false);
            requests.setDateTime(LocalDateTime.now());
            requestRepository.save(requests);

            mentor.getRequests().add(requests);
            mentorRepository.save(mentor);
            return "Successfully Sent Request";
        }
    }

}
