package com.serviceharbor.auth.controller;

import com.serviceharbor.auth.model.User;


import com.serviceharbor.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/service_provider")
    public ResponseEntity<User> getProfileByRoleAndEmail(
            @RequestParam String role, @RequestParam String email) {
        try {
            User user = userService.getUserByRoleAndEmail(role, email);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            // Log error and return bad request response
            return ResponseEntity.badRequest().body(null);
        }
    }
}
//
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User newUser = userService.registerUser(user);
//        return ResponseEntity.ok(newUser);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
//        User user = userService.authenticateUser(loginRequest);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(401).build(); // Unauthorized
//        }
//    }
//
//      @GetMapping("/profile")
//    public ResponseEntity<User> getProfileByRoleAndUsername(@RequestParam String role, @RequestParam String username) {
//        User user = (User) userService.allUsers();
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @PostMapping("/authenticate")
//    public String authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
//            return jwtUtil.generateToken(userDetails.getUsername());
//        } catch (AuthenticationException e) {
//            throw new Exception("Invalid username or password", e);
//        }
//    }

    // Other endpoints related to user management
