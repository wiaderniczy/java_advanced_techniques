package com.example.lab06.repository;

import com.example.lab06.models.Pricing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends CrudRepository<Pricing, String> {
}
