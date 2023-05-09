package com.example.lab06.repository;

import com.example.lab06.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payments, Long> {
}
