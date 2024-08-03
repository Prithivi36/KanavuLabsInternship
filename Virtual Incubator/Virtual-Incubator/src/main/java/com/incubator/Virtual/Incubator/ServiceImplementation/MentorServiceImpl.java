package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.MentorDto;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Repository.MentorRepository;
import com.incubator.Virtual.Incubator.Service.MentorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {
    ModelMapper modelMapper;
    MentorRepository mentorRepository;
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
}
