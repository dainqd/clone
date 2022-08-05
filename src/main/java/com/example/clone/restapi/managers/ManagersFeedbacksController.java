package com.example.clone.restapi.managers;

import com.example.clone.entity.Feedbacks;
import com.example.clone.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/managers/feedbacks")
@PreAuthorize("hasRole('MODERATOR')")
public class ManagersFeedbacksController {
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
}
