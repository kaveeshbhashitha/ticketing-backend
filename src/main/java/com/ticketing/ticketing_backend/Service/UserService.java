package com.ticketing.ticketing_backend.Service;
import com.ticketing.ticketing_backend.Model.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(String userId);
    User getUserNameById(String userId);
    User getUserByUserEmail(String userEmail);
    List<User> getAllUsers();
    void deleteUser(String userId);
    String sendRecoveryCode(String userEmail);
    boolean verifyRecoveryCode(String userEmail, String recoveryCode);
    User updatePassword(String userEmail, String newPassword);
    public String register(User user);
    public String logout(HttpSession session);
    public String login(User loginRequest);
    public User updateUser(String userId, User user);
}
