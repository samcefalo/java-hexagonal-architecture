package me.samcefalo.courses.java.hexagonal.adapters.out.repository;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {


}
