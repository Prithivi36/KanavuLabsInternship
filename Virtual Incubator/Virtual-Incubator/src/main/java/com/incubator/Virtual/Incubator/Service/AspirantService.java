package com.incubator.Virtual.Incubator.Service;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import com.incubator.Virtual.Incubator.Dto.RequestsDto;
import com.incubator.Virtual.Incubator.Entity.Mentor;
import com.incubator.Virtual.Incubator.Entity.Requests;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AspirantService {
    String saveAspirant(AspirantDto aspirantDto);
    AspirantDto getAspirant(Long id);
    List<AspirantDto> getAllAspirants();
    List<RequestsDto<Mentor>> viewMentorOffers(Long id);
}
