package com.ticketing.ticketing_backend.Repository;
import com.ticketing.ticketing_backend.Model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {
}
