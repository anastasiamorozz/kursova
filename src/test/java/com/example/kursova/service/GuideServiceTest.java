package com.example.kursova.service;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.model.Guide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GuideServiceTest {

    @Mock
    private GuideDAO guideDAO;

    private GuideService guideService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guideService = new GuideService();
        guideService.setGuideDAO(guideDAO);
    }

    @Test
    void testGetAllGuides() {
        List<Guide> expectedGuides = Arrays.asList(new Guide(1, "Guide 1", "English", "123456789"),
                new Guide(2, "Guide 2", "Spanish", "987654321"));
        when(guideDAO.getAllGuides()).thenReturn(expectedGuides);
        List<Guide> actualGuides = guideService.getAllGuides();
        assertEquals(expectedGuides, actualGuides);
    }

    @Test
    void testAddGuide() {
        Guide guide = new Guide(1, "Guide 1", "English", "123456789");
        guideService.addGuide(guide);
        verify(guideDAO, times(1)).addGuide(guide);
    }

    @Test
    void testUpdateGuide() {
        Guide guide = new Guide(1, "Guide 1", "English", "123456789");
        guideService.updateGuide(guide);
        verify(guideDAO, times(1)).updateGuide(guide);
    }

    @Test
    void testDeleteGuide() {
        int guideId = 1;
        guideService.deleteGuide(guideId);
        verify(guideDAO, times(1)).deleteGuide(guideId);
    }

    @Test
    void testSearchGuides() {
        String keyword = "Guide";
        List<Guide> expectedGuides = Arrays.asList(new Guide(1, "Guide 1", "English", "123456789"));
        when(guideDAO.getAllGuides()).thenReturn(expectedGuides);
        List<Guide> actualGuides = guideService.searchGuides(keyword);
        assertEquals(expectedGuides, actualGuides);
    }
}