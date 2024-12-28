package com.ticketing.ticketing_backend.Repository;
import com.ticketing.ticketing_backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(String userId);
    User findByUserEmail(String username);
    @Query("SELECT u.username FROM User u WHERE u.id = :userId")
    Optional<User> findUsernameByUserId(String userId);
}
