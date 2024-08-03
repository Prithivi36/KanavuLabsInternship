package com.incubator.Virtual.Incubator.ServiceImplementation;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Entity.Aspirant;
import com.incubator.Virtual.Incubator.Repository.AspirantRepository;
import com.incubator.Virtual.Incubator.Service.AspirantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AspirantServiceImpl implements AspirantService {

    AspirantRepository aspirantRepository;
    ModelMapper modelMapper;

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
}
