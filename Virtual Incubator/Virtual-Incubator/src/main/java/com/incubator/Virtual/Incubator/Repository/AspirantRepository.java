package com.incubator.Virtual.Incubator.Repository;

import com.incubator.Virtual.Incubator.Entity.Aspirant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AspirantRepository extends JpaRepository<Aspirant, Long> {
}
