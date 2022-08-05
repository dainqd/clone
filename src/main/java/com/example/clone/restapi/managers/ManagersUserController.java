package com.example.clone.restapi.managers;

import com.example.clone.entity.User;
import com.example.clone.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/managers/user")
@PreAuthorize("hasRole('MODERATOR')")
public class ManagersUserController {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceimpl;

    @GetMapping("/views/list")
    public ResponseEntity<List<User>> getList(){
        return ResponseEntity.ok(userDetailsServiceimpl.findAll());
    }

    @GetMapping("/views/list/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<User> optionalUser = userDetailsServiceimpl.findById(id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }
}
