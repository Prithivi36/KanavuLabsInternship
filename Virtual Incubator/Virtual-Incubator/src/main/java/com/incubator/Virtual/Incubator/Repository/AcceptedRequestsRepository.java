package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.AcceptedRequest;
import com.incubator.Virtual.Incubator.Entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcceptedRequestsRepository extends JpaRepository<AcceptedRequest,Long> {
    Optional<AcceptedRequest> findByAspIdAndMntId(Long aspId, Long mntId);
}
