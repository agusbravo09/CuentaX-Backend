package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.model.Feedback;
import com.example.FinanzApp.repository.IFeedbackRepository;
import com.example.FinanzApp.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private IFeedbackRepository feedbackRepo;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        Feedback feed = feedbackRepo.save(feedback);
        return feed;
    }
}
