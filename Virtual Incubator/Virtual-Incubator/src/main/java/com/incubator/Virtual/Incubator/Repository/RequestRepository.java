package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Requests,Long> {
    Requests findByAspIdAndMntId(Long aspId,Long mntId);
}
