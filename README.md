# 🗺️ Tour Agency Management App

This is a desktop Java application designed to manage tours and guides in a travel agency. The application features a graphical interface implemented using JavaFX, works with an embedded SQL-compatible database (like H2), and includes unit tests based on JUnit 5.

## 📌 Key Features

- ➕ Add, edit, and delete tours
- 👨‍💼 Manage guides and their spoken languages
- 🔍 Filter and search tours
- 🗄️ Persist and retrieve data using an embedded database
- 💻 User-friendly graphical interface
- ✅ Unit testing of service logic (without mocks)

## 📁 Project Structure

```
src/
├── controller/       # JavaFX controllers (UI logic)
├── model/            # Data models (Tour, Guide, Language)
├── service/          # Business logic (TourService, GuideService)
├── util/             # Utility classes (formatting, parsing, etc.)
├── enums/            # Enumerations (tour types, languages)
├── resources/        # FXML files, styles
└── tests/            # Unit tests using JUnit 5
```

## 🛠️ Technologies Used

- **Java 17+**
- **JavaFX** — GUI framework
- **JUnit 5** — unit testing
- **Maven / Gradle** — project build tools
- **H2 / SQLite** — lightweight embedded SQL database

## 🚀 How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/tour-agency-app.git
   cd tour-agency-app
   ```

2. Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

3. Run the main class:
   ```
   Main.java or App.java
   ```

## 🧪 Testing

To run the unit tests:

### Maven
```bash
mvn test
```

### Gradle
```bash
./gradlew test
```

- Core business logic is covered with unit tests
- Tests are written **without using mocks**
- JUnit 5 framework is used

## 📈 Future Improvements

- Integrate with a real database via JDBC or ORM (e.g., Hibernate)
- Deploy as a web application using Spring Boot
- Implement user authentication
- Add reporting and export (e.g., to PDF)
- Localize the user interface

## 📄 License

This project was created as part of an academic coursework. All third-party resources used are permitted according to their respective licenses.

## 👤 Author

- Developer: **Moroz Anastasiia**
- Institution: **Lviv Polytechnic National Univercity**
- Year: **2025**
