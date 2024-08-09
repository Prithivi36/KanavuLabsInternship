package com.incubator.Virtual.Incubator.Service;

import com.incubator.Virtual.Incubator.Dto.StartupProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StartupService {
    String saveStartup(Long personId, StartupProfileDto startupProfileDto, String type);
}
