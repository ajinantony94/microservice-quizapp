package com.microservice.quiz.quiz_service.controller;


import com.microservice.quiz.quiz_service.model.QuestionWrapper;
import com.microservice.quiz.quiz_service.model.Quizdto;
import com.microservice.quiz.quiz_service.model.Response;
import com.microservice.quiz.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody Quizdto quizdto){
        return quizService.createQuiz(quizdto.getCategoryName(),quizdto.getNumQuestion(),quizdto.getTitle());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id,responses);
    }
}
