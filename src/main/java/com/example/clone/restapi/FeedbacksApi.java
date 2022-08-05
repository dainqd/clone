package com.example.clone.restapi;

import com.example.clone.entity.Feedbacks;
import com.example.clone.service.FeedbackService;
import com.example.clone.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedbacks")
@PreAuthorize("hasRole('USER')")
public class FeedbacksApi {

//    @Autowired
    final FeedbackService feedbackService;

    @GetMapping()
    public ResponseEntity<List<Feedbacks>> getList(){
        return ResponseEntity.ok(feedbackService.getListByStatus(Enums.FeedbackStatus.active));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
        Optional<Feedbacks> optionalFeedbacks = feedbackService.getListByIdAndStatus(id, Enums.FeedbackStatus.active);
        if (!optionalFeedbacks.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(feedbackService.getListByIdAndStatus(id, Enums.FeedbackStatus.active));
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
