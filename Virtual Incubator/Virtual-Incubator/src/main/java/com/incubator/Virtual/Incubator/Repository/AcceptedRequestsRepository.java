package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.AcceptedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedRequestsRepository extends JpaRepository<AcceptedRequest,Long> {
}
