package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Requests,Long> {
    Optional<Requests> findByAspIdAndMntId(Long aspId, Long mntId);
}
