package com.example.FinanzApp.service;

import com.example.FinanzApp.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> getAllFeedbacks();
    Feedback createFeedback(Feedback feedback);

}
