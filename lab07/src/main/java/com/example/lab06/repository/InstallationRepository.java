package com.example.lab06.repository;

import com.example.lab06.models.Installation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallationRepository extends CrudRepository<Installation, Long> {
}
