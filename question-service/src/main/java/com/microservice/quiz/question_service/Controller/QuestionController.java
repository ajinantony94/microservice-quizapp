package com.microservice.quiz.question_service.Controller;


import com.microservice.quiz.question_service.Model.Question;
import com.microservice.quiz.question_service.Model.QuestionWrapper;
import com.microservice.quiz.question_service.Model.Response;
import com.microservice.quiz.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getById(@PathVariable int id){
        return questionService.getById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        return questionService.getByCategory(category);
    }

    //to add some new questions!!!!
    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
       return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ){
        return questionService.getQuestionsForQuiz(category,numQ);
    }

    @PostMapping("/quiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionFromId(questionIds);
    }
    @PostMapping("/submit")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }




}
