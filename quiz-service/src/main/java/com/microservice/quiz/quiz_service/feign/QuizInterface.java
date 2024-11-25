package com.microservice.quiz.quiz_service.feign;




import com.microservice.quiz.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ);

    @PostMapping("questions/quiz")
    public ResponseEntity<List<com.microservice.quiz.quiz_service.model.QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);
    @PostMapping("questions/submit")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
