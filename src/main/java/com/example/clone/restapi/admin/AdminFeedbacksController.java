package com.example.clone.restapi.admin;

import com.example.clone.entity.Feedbacks;
import com.example.clone.service.FeedbackService;
import com.example.clone.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin/feedbacks")
public class AdminFeedbacksController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping()
    public ResponseEntity<List<Feedbacks>> getLists(){
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Integer id){
        Optional<Feedbacks> optionalFeedbacks = feedbackService.findById(id);
        if (!optionalFeedbacks.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Feedbacks> create(@RequestBody Feedbacks feedbacks){
        return ResponseEntity.ok(feedbackService.save(feedbacks));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedbacks> update(@PathVariable Integer id, @RequestBody Feedbacks feedbacks){
        Optional<Feedbacks> optionalFeedbacks = feedbackService.findById(id);
        if ((!optionalFeedbacks.isPresent())){
            ResponseEntity.badRequest().build();
        }
        Feedbacks existFeedbacks = optionalFeedbacks.get();

        existFeedbacks.setUsername(feedbacks.getUsername());
        existFeedbacks.setStar(feedbacks.getStar());
        existFeedbacks.setContent(feedbacks.getContent());
        existFeedbacks.setStatus(feedbacks.getStatus());
        return ResponseEntity.ok(feedbackService.save(existFeedbacks));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!feedbackService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        feedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
