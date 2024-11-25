package com.microservice.quiz.question_service.Service;


import com.microservice.quiz.question_service.Model.Question;
import com.microservice.quiz.question_service.Model.QuestionWrapper;
import com.microservice.quiz.question_service.Model.Response;
import com.microservice.quiz.question_service.Repository.QuestionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO questionDAO;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<Optional<Question>> getById(int id) {
        return new ResponseEntity<>(questionDAO.findById(id),HttpStatus.FOUND);
    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {

         questionDAO.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
        List<Integer> questions = questionDAO.findRandomQuestionsByCategory(category,numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id: questionIds) {
            questions.add(questionDAO.findById(id).get());
        }

        for (Question question: questions){
            QuestionWrapper wrappers = new QuestionWrapper();
            wrappers.setId(question.getId());
            wrappers.setTitle(question.getQuestionText());
            wrappers.setOption1(question.getOption_a());
            wrappers.setOption2(question.getOption_b());
            wrappers.setOption3(question.getOption_c());
            wrappers.setOption4(question.getOption_d());
            wrapper.add(wrappers);

        }

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;
        for(Response response: responses){
            Question question = questionDAO.findById(response.getId()).get();
            if(response.getResponse().equals(question.getCorrectOption())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
