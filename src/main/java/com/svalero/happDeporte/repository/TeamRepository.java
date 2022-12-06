package com.svalero.happDeporte.repository;

import com.svalero.happDeporte.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
