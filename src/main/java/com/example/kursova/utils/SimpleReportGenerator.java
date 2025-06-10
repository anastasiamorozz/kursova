package com.example.kursova.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;


import com.example.kursova.model.Tour;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleReportGenerator {

    public void generatePdfReport(List<Tour> tours, Stage primaryStage) {
        // Створення FileChooser для вибору місця збереження файлу
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Відкриваємо діалогове вікно для вибору шляху
        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            try {
                // Створення PDF документа
                PdfDocument pdf = new PdfDocument(new PdfWriter(selectedFile));

                PdfFont font = PdfFontFactory.createFont(
                        "src/main/resources/com/example/kursova/fonts/DejaVuSans.ttf",
                        PdfEncodings.IDENTITY_H
                );


                // Створюємо документ і встановлюємо шрифт
                Document document = new Document(pdf);
                document.setFont(font);

                // Додаємо заголовок
                document.add(new Paragraph("Звіт по турах").setBold().setFontSize(16));

                // Створюємо таблицю для відображення турів
                Table table = new Table(5);
                table.addHeaderCell("Назва");
                table.addHeaderCell("Тип туру");
                table.addHeaderCell("Транспорт");
                table.addHeaderCell("Ціна");
                table.addHeaderCell("Мова");

                // Додаємо тури в таблицю
                for (Tour tour : tours) {
                    table.addCell(tour.getTitle());
                    table.addCell(tour.getTourType().toString());
                    table.addCell(tour.getTransport().toString());
                    table.addCell(String.valueOf(tour.getPrice()));
                    table.addCell(tour.getLanguage().toString());
                }

                // Додаємо таблицю до документа
                document.add(table);

                // Закриваємо документ
                document.close();
                System.out.println("Звіт успішно згенеровано в PDF за шляхом: " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Помилка при генерації звіту");
            }
        } else {
            System.out.println("Користувач скасував вибір файлу.");
        }
    }
}
