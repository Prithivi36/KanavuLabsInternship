package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}
