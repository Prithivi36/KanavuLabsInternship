package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.StartupProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupRepository extends JpaRepository<StartupProfile,Long> {
}
