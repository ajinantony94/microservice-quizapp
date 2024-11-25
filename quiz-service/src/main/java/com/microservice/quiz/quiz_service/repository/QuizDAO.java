package com.microservice.quiz.quiz_service.repository;

import com.microservice.quiz.quiz_service.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
