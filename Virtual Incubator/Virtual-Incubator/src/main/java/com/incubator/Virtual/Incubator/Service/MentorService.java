package com.incubator.Virtual.Incubator.Service;

import com.incubator.Virtual.Incubator.Dto.MentorDto;
import com.incubator.Virtual.Incubator.Dto.RequestsDto;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentorService {
    String saveMentor(MentorDto mentorDto);
    MentorDto getMentor(Long id);
    List<MentorDto> getAllMentors();
    String mentorOffer(Long aspId,Long mntId);
    List<RequestsDto<Aspirant>> viewMentorRequest(Long id);
    String acceptAspirantRequest(Long id);
}
