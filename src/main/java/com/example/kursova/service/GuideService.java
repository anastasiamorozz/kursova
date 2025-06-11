package com.example.kursova.service;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.model.Guide;

import java.util.List;
import java.util.stream.Collectors;

public class GuideService {
    private GuideDAO guideDAO;

    public GuideService() {
        this.guideDAO = new GuideDAO();
    }

    public void setGuideDAO(GuideDAO guideDAO) {
        this.guideDAO = guideDAO;
    }

    public List<Guide> getAllGuides() {
        return guideDAO.getAllGuides();
    }

    public void addGuide(Guide guide) {
        guideDAO.addGuide(guide);
    }

    public void updateGuide(Guide guide) {
        guideDAO.updateGuide(guide);
    }

    public void deleteGuide(int guideId) {
        guideDAO.deleteGuide(guideId);
    }

    public List<Guide> searchGuides(String keyword) {
        return guideDAO.getAllGuides().stream()
                .filter(guide -> guide.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}