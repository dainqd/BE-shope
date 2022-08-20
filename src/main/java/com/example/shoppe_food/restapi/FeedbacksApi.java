package com.example.shoppe_food.restapi;

import com.example.shoppe_food.entity.Feedbacks;
import com.example.shoppe_food.service.FeedbackService;
import com.example.shoppe_food.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedbacks")
public class FeedbacksApi {
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
}
