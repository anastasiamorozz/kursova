package com.example.kursova.dao;

import com.example.kursova.model.Guide;
import com.example.kursova.utils.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GuideDAOTest {

    private final GuideDAO guideDAO = new GuideDAO();
    private Guide testGuide;

    @BeforeAll
    public void setupDatabase() throws Exception {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS guides (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, language TEXT, phone TEXT)");
        }
    }

    @BeforeEach
    public void cleanDatabase() throws Exception {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM guides");
        }
        testGuide = new Guide(0, "Test Guide", "English", "123456789");
    }

    @Test
    @Order(1)
    public void testAddGuide() {
        guideDAO.addGuide(testGuide);
        List<Guide> guides = guideDAO.getAllGuides();
        assertEquals(1, guides.size());
        assertEquals("Test Guide", guides.get(0).getName());
    }

    @Test
    @Order(2)
    public void testGetAllGuides() {
        guideDAO.addGuide(testGuide);
        List<Guide> guides = guideDAO.getAllGuides();
        assertFalse(guides.isEmpty());
    }

    @Test
    @Order(3)
    public void testGetGuideById() {
        guideDAO.addGuide(testGuide);
        int id = guideDAO.getAllGuides().get(0).getId();
        Guide retrieved = guideDAO.getGuideById(id);
        assertNotNull(retrieved);
        assertEquals("Test Guide", retrieved.getName());
    }

    @Test
    @Order(4)
    public void testUpdateGuide() {
        guideDAO.addGuide(testGuide);
        Guide original = guideDAO.getAllGuides().get(0);
        original.setName("Updated Guide");
        guideDAO.updateGuide(original);

        Guide updated = guideDAO.getGuideById(original.getId());
        assertEquals("Updated Guide", updated.getName());
    }

    @Test
    @Order(5)
    public void testDeleteGuide() {
        guideDAO.addGuide(testGuide);
        int id = guideDAO.getAllGuides().get(0).getId();
        guideDAO.deleteGuide(id);
        Guide deleted = guideDAO.getGuideById(id);
        assertNull(deleted);
    }
}
