package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.MentorDto;
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
    AcceptedRequestsRepository acceptedRequestsRepository;
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

        if (requestRepository.findByAspIdAndMntId(aspId,mntId).isPresent() || acceptedRequestsRepository.findByAspIdAndMntId(aspId,mntId).isPresent()) {
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

    public List<RequestsDto<Aspirant>> viewMentorRequest(Long id){
        List<Requests> request= mentorRepository.findById(id).orElseThrow(
                        ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant not found"))
                .getRequests();

        List<RequestsDto<Aspirant>> rqstDto= request.stream().map((rqst)->{
            RequestsDto<Aspirant> modified=new RequestsDto<>();
            modified.setPerson(aspirantRepository.findById(rqst.getAspId()).orElseThrow(
                    ()->new ExceptionDetail(HttpStatus.NOT_FOUND,"Aspirant not found")));
            modified.setMessage(rqst.getMessage());
            modified.setDateTime(rqst.getDateTime());
            modified.setRqstId(rqst.getId());
            return modified;
        }).toList();
        return rqstDto;
    }

    public String acceptAspirantRequest(Long id){
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
}
