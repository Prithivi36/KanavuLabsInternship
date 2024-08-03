package com.incubator.Virtual.Incubator.Service;

import com.incubator.Virtual.Incubator.Dto.AspirantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AspirantService {
    String saveAspirant(AspirantDto aspirantDto);
    AspirantDto getAspirant(Long id);
    List<AspirantDto> getAllAspirants();
}
