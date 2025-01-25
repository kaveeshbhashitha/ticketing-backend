package com.ticketing.ticketing_backend.Controller;
import com.ticketing.ticketing_backend.Dto.AuthRequest;
import com.ticketing.ticketing_backend.Dto.AuthenticationResponse;
import com.ticketing.ticketing_backend.JWT.JwtService;
import com.ticketing.ticketing_backend.Model.User;
import com.ticketing.ticketing_backend.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://oficialticketing-frontend.netlify.app", "http://localhost:5173"})
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    // Create a new user
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    // Get user by ID
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getUserNameByID/{id}")
    public String getUsernameById(@PathVariable("id") String userId) {
        User user = userService.getUserNameById(userId);
        String userName=user.getFirstName();
        return userName;
    }
    @GetMapping("/getUserByEmail/{id}")
    public Optional<User> getUserByEmail(@PathVariable("id") String userEmail) {
        return userService.getUserByUserEmail(userEmail);
    }
    // Get all users
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }
    /*
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        // Authenticate user in service
        String loginMessage = userService.login(loginRequest);
        if ("Login successful".equals(loginMessage)) {
            Optional<User> authenticatedUser = userService.getUserByUserEmail(loginRequest.getUserEmail());
            // Generate JWT token if login successful
            String token = JwtUtil.generateToken(loginRequest.getUserEmail()); // Use userEmail for token generation
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", loginRequest.getUserEmail());
            response.put("role", authenticatedUser.getUserRole());
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, loginMessage);
        }
    }
    */
    @PostMapping("/login")
    public AuthenticationResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserEmail());
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        return userService.logout(session);
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }
    @PostMapping("/send-code")
    public String sendRecoveryCode(@RequestBody Map<String, String> payload) {
        String userEmail = payload.get("userEmail");
        if (userEmail == null || userEmail.isBlank()) {
            throw new RuntimeException("Email cannot be empty.");
        }
        return userService.sendRecoveryCode(userEmail);
    }
    @PostMapping("/verify-code")
    public boolean verifyRecoveryCode(@RequestParam String userEmail, @RequestParam String recoveryCode) {
        return userService.verifyRecoveryCode(userEmail, recoveryCode);
    }
    @PostMapping("/update-password")
    public User updatePassword(@RequestParam String userEmail, @RequestParam String newPassword) {
        return userService.updatePassword(userEmail, newPassword);
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody User user) {

        try {
            User updatedUser = userService.updateUser(userId, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception (optional)
            System.err.println("Error updating user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
