package com.microservice.quiz.quiz_service.service;






import com.microservice.quiz.quiz_service.feign.QuizInterface;
import com.microservice.quiz.quiz_service.model.QuestionWrapper;
import com.microservice.quiz.quiz_service.model.Quiz;


import com.microservice.quiz.quiz_service.model.Response;
import com.microservice.quiz.quiz_service.repository.QuizDAO;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizInterface quizInterface;

    @Autowired
    QuizDAO quizDAO;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

//        try {
            List<Integer> question = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
            if(question==null){
                System.out.println("its null");
            }
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(question);
            quizDAO.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
//        }catch(FeignException fe){
//            return new ResponseEntity<>("error calling the quiz service"+fe.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz = quizDAO.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> question = quizInterface.getQuestionFromId(questionIds);
       return question;

    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
