package com.example.kursova.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    public static void init() {
        String createGuidesTable = """
            CREATE TABLE IF NOT EXISTS guides (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                language TEXT NOT NULL,
                phone TEXT
            );
        """;

        String createHotelsTable = """
            CREATE TABLE IF NOT EXISTS hotels (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                stars INTEGER NOT NULL CHECK(stars >= 1 AND stars <= 5),
                country TEXT NOT NULL,
                city TEXT NOT NULL,
                address TEXT,
                phone TEXT,
                email TEXT
            );
        """;

        String createToursTable = """
            CREATE TABLE IF NOT EXISTS tours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                tour_type TEXT NOT NULL,
                transport TEXT NOT NULL,
                meal_type TEXT NOT NULL,
                days INTEGER,
                price REAL,
                hotel_name TEXT,
                language TEXT,
                description TEXT,
                guide_id INTEGER,
                FOREIGN KEY (hotel_name) REFERENCES hotels(name),
                FOREIGN KEY (guide_id) REFERENCES guides(id)
            );
        """;

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createGuidesTable);
            stmt.execute(createHotelsTable);
            stmt.execute(createToursTable);

            String insertTestHotels = """
                INSERT INTO hotels (name, stars, country, city, address, phone, email) VALUES
                ('Hotel Lviv', 3, 'Україна', 'Львів', 'вул. Городоцька, 15', '+380322111111', 'info@hotellviv.ua'),
                ('Premier Palace', 5, 'Україна', 'Київ', 'бульв. Тараса Шевченка, 5', '+380442222222', 'contact@premier.com'),
                ('Ramada Plaza', 4, 'Польща', 'Варшава', 'ul. Marszałkowska 80', '+48221234567', 'booking@ramadaplaza.pl'),
                ('Hotel Europa', 3, 'Німеччина', 'Берлін', 'Alexanderplatz 7', '+4930123456', 'info@hoteleuropa.de'),
                ('Sunset Resort', 5, 'Іспанія', 'Барселона', 'Carrer de Mallorca 42', '+34933111222', 'sunset@resort.es'),
                ('Hotel Riviera', 4, 'Італія', 'Рим', 'Via Nazionale 33', '+390642234455', 'riviera@hotel.it'),
                ('Alpine Lodge', 3, 'Австрія', 'Зальцбург', 'Hauptstraße 22', '+4366223344', 'info@alpinelodge.at'),
                ('Nordic Inn', 4, 'Норвегія', 'Осло', 'Karl Johans gate 15', '+4722334455', 'stay@nordicinn.no'),
                ('Blue Lagoon', 5, 'Ісландія', 'Рейкявік', 'Laugavegur 105', '+3545555555', 'blue@lagoon.is'),
                ('Hotel Parisienne', 4, 'Франція', 'Париж', 'Rue de Rivoli 25', '+33142223344', 'contact@parisienne.fr'),
                ('Metro Hotel', 3, 'США', 'Нью-Йорк', '7th Avenue 50', '+12125551234', 'hello@metrohotel.us'),
                ('Tokyo Garden', 4, 'Японія', 'Токіо', 'Shinjuku 3-12', '+81312345678', 'info@tokyogarden.jp'),
                ('Coral Beach', 5, 'Єгипет', 'Хургада', 'Sheraton Road 88', '+20653456789', 'coral@beach.eg'),
                ('Desert Rose', 4, 'Марокко', 'Марракеш', 'Rue Yves Saint Laurent', '+2125556677', 'info@desertrose.ma'),
                ('Sofia Grand', 3, 'Болгарія', 'Софія', 'Vitosha Blvd 101', '+35924123456', 'contact@sofiagrand.bg'),
                ('Hotel Helsinki', 4, 'Фінляндія', 'Гельсінкі', 'Mannerheimintie 10', '+35891234567', 'info@hotelhelsinki.fi'),
                ('Royal Victoria', 5, 'Велика Британія', 'Лондон', 'Victoria Street 45', '+442071234567', 'booking@royalvic.uk'),
                ('Hotel Budapest', 3, 'Угорщина', 'Будапешт', 'Andrássy út 25', '+3612345678', 'budapest@hotel.hu'),
                ('Caspian Pearl', 4, 'Азербайджан', 'Баку', 'Neftchilar Ave 12', '+99412456789', 'pearl@caspian.az'),
                ('Hotel Tbilisi', 3, 'Грузія', 'Тбілісі', 'Rustaveli Ave 18', '+99532234567', 'contact@hoteltbilisi.ge');
            """;

            String insertTestGuides = """
                INSERT INTO guides (name, language, phone) VALUES
                ('Олена Шевченко', 'UKRAINIAN', '+380631112233'),
                ('John Smith', 'ENGLISH', '+447911123456'),
                ('Hans Müller', 'GERMAN', '+4915112345678'),
                ('Anna Kowalska', 'POLISH', '+48601234567'),
                ('Carlos García', 'SPANISH', '+34699111222'),
                ('Marie Dubois', 'FRENCH', '+33678901234'),
                ('Ігор Коваль', 'UKRAINIAN', '+380671234567'),
                ('Emily Johnson', 'ENGLISH', '+14151234567'),
                ('Lukas Schmidt', 'GERMAN', '+492221112233'),
                ('Sophie Lefevre', 'FRENCH', '+33123456789');
            """;

            String insertTestTours = """
INSERT INTO tours (title, tour_type, transport, meal_type, days, price, hotel_name, language, description, guide_id) VALUES
('Карпати Вихідного Дня', 'VACATION', 'BUS', 'BREAKFAST', 3, 2500.00, 'Карпатський рай', 'UKRAINIAN', 'Чудова подорож у Карпати.', 1),
('London Classic Tour', 'EXCURSION', 'PLANE', 'ALL_INCLUSIVE', 5, 850.00, 'The Royal Stay', 'ENGLISH', 'Explore the landmarks of London.', 2),
('Berlin Wall Experience', 'EXCURSION', 'TRAIN', 'BREAKFAST', 4, 780.00, 'Berliner Hof', 'GERMAN', 'A dive into German history.', 3),
('Weekend in Warsaw', 'EXCURSION', 'BUS', 'ALL_INCLUSIVE', 3, 600.00, 'Polonia Grand', 'POLISH', 'Discover Warsaw''s charm.', 4),
('Barcelona Beach Trip', 'VACATION', 'PLANE', 'ALL_INCLUSIVE', 7, 1200.00, 'Costa del Mar', 'SPANISH', 'Sunny vacation at the seaside.', 5),
('Paris Romance', 'VACATION', 'PLANE', 'BREAKFAST', 4, 950.00, 'Eiffel Dreams', 'FRENCH', 'Fall in love with Paris.', 6),
('Львів на Вікенд', 'EXCURSION', 'TRAIN', 'BREAKFAST', 2, 1200.00, 'Львівський Затишок', 'UKRAINIAN', 'Культурна подорож у серце Львова.', 7),
('English Castle Tour', 'EXCURSION', 'BUS', 'BREAKFAST', 5, 1100.00, 'Historic Stay', 'ENGLISH', 'Visit England''s finest castles.', 8),
('Bavarian Alps Adventure', 'VACATION', 'BUS', 'ALL_INCLUSIVE', 6, 1300.00, 'Alpen Resort', 'GERMAN', 'Adventure in the Bavarian Alps.', 9),
('Châteaux of Loire', 'EXCURSION', 'TRAIN', 'ALL_INCLUSIVE', 4, 1400.00, 'Loire Elegance', 'FRENCH', 'Explore French heritage.', 10),
('Madrid Cultural Escape', 'EXCURSION', 'PLANE', 'BREAKFAST', 5, 970.00, 'Madrid Plaza', 'SPANISH', 'Discover Madrid''s culture.', 5),
('Верховина і Гуцульщина', 'EXCURSION', 'BUS', 'ALL_INCLUSIVE', 3, 1500.00, 'Гуцульський Край', 'UKRAINIAN', 'Етно-тур з гуцульським колоритом.', 1),
('Tour de France (Mini)', 'EXCURSION', 'PLANE', 'BREAKFAST', 5, 1100.00, 'City Chic', 'FRENCH', 'Visit top French cities.', 6),
('Valencia Wine Tour', 'VACATION', 'TRAIN', 'ALL_INCLUSIVE', 4, 1150.00, 'Bodega Inn', 'SPANISH', 'Enjoy the best wines of Spain.', 5),
('Oxford & Cambridge', 'EXCURSION', 'BUS', 'BREAKFAST', 2, 700.00, 'Academic Suites', 'ENGLISH', 'Visit the UK''s top universities.', 2),
('Wrocław History Tour', 'EXCURSION', 'TRAIN', 'BREAKFAST', 3, 650.00, 'Old Town Inn', 'POLISH', 'Explore Poland''s gems.', 4),
('Українське Полісся', 'VACATION', 'BUS', 'ALL_INCLUSIVE', 2, 950.00, 'Поліський Притулок', 'UKRAINIAN', 'Лісові простори Полісся.', 7),
('Frankfurt Finance Trip', 'EXCURSION', 'PLANE', 'BREAKFAST', 3, 1350.00, 'Finance Hub Hotel', 'GERMAN', 'Business travel and networking.', 9),
('Kraków Religious Trail', 'EXCURSION', 'TRAIN', 'BREAKFAST', 4, 800.00, 'Saintly Stay', 'POLISH', 'Pilgrimage to sacred places.', 4),
('Nice and Monaco', 'VACATION', 'BUS', 'ALL_INCLUSIVE', 5, 1600.00, 'Azure Coast Hotel', 'FRENCH', 'Luxury vacation by the sea.', 10),
('Kyiv City Break', 'EXCURSION', 'TRAIN', 'BREAKFAST', 2, 1000.00, 'Київський Хостел', 'UKRAINIAN', 'Столиця зсередини.', 7),
('Seville Flamenco Tour', 'EXCURSION', 'PLANE', 'ALL_INCLUSIVE', 4, 1250.00, 'Casa del Flamenco', 'SPANISH', 'Dive into Spanish dance culture.', 5),
('Zurich Winter Magic', 'VACATION', 'TRAIN', 'ALL_INCLUSIVE', 5, 1900.00, 'Alpine Snow', 'GERMAN', 'Winter in the Alps.', 3),
('Uzhhorod Wine & Cheese', 'EXCURSION', 'BUS', 'BREAKFAST', 2, 1300.00, 'Закарпатська Винниця', 'UKRAINIAN', 'Вино, сири і Карпати.', 1),
('Lviv Jazz Weekend', 'EXCURSION', 'TRAIN', 'BREAKFAST', 2, 1150.00, 'Леополіс Люкс', 'UKRAINIAN', 'Музичний драйв Львова.', 7),
('English Museum Pass', 'EXCURSION', 'BUS', 'BREAKFAST', 3, 980.00, 'Museum Inn', 'ENGLISH', 'Explore UK''s top museums.', 8),
('Romantic Paris Nights', 'VACATION', 'PLANE', 'ALL_INCLUSIVE', 4, 1400.00, 'Parisian Rose', 'FRENCH', 'Evenings in the City of Love.', 6),
('Zakopane Winter Tour', 'VACATION', 'BUS', 'BREAKFAST', 4, 900.00, 'Snowy Peaks', 'POLISH', 'Winter in the Tatra Mountains.', 4),
('Poltava Historic Journey', 'EXCURSION', 'TRAIN', 'BREAKFAST', 3, 890.00, 'Славний Курінь', 'UKRAINIAN', 'Сторінки історії Полтавщини.', 1),
('French Riviera', 'VACATION', 'PLANE', 'ALL_INCLUSIVE', 6, 1850.00, 'Sun & Sea', 'FRENCH', 'Best beaches in France.', 10);
""";



            String checkHotels = "SELECT COUNT(*) AS total FROM hotels";
            var rs = stmt.executeQuery(checkHotels);
            if (rs.next() && rs.getInt("total") == 0) {
                stmt.execute(insertTestHotels);
                System.out.println("📥 Додано тестові готелі");
            }

            String checkGuides = "SELECT COUNT(*) AS total FROM guides";
            var rs2 = stmt.executeQuery(checkGuides);
            if (rs2.next() && rs2.getInt("total") == 0) {
                stmt.execute(insertTestGuides);
                System.out.println("📥 Додано тестових гідів");
            }

            String checkTours = "SELECT COUNT(*) AS total FROM tours";
            var rs3 = stmt.executeQuery(checkTours);
            if (rs3.next() && rs3.getInt("total") == 0) {
                stmt.execute(insertTestTours);
                System.out.println("📥 Додано тестові тури");
            }

            System.out.println("✅ База даних ініціалізована (guides, hotels, tours).");

        } catch (SQLException e) {
            System.err.println("❌ Помилка ініціалізації БД: " + e.getMessage());
        }
    }
}
