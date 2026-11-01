# Java Spam Message Detector

A beginner-friendly machine-learning project built in **Java 17**. It classifies a text message as `spam` or `ham` (normal) using TF-IDF text features and a Multinomial Naive Bayes classifier.

> This is an educational portfolio project, not a production spam filter. The included dataset is small and synthetic, so predictions can be wrong.

## Features

- Loads labeled messages from a CSV file.
- Normalizes text and extracts word tokens.
- Converts text into TF-IDF feature vectors.
- Trains a Naive Bayes classifier with Laplace smoothing.
- Accepts messages interactively in the terminal.
- Includes automated tests.

## Tech stack

- Java 17
- Maven
- JUnit 5 (tests only)
- TF-IDF + Multinomial Naive Bayes (implemented in this project)

## Project structure

```text
java-spam-detector/
├── data/
│   └── messages.csv
├── docs/
│   ├── INTERVIEW_QUESTIONS.md
│   └── LEARNING_GUIDE.md
├── src/
│   ├── main/java/com/portfolio/spam/
│   │   ├── CsvDatasetLoader.java
│   │   ├── Main.java
│   │   ├── Message.java
│   │   ├── NaiveBayesClassifier.java
│   │   ├── SpamDetector.java
│   │   ├── TextPreprocessor.java
│   │   └── TfidfVectorizer.java
│   └── test/java/com/portfolio/spam/
│       └── SpamDetectorTest.java
├── .gitignore
└── pom.xml
```

## Requirements

Install:
1. JDK 17 or later.
2. Apache Maven 3.8 or later.
3. Git (to publish the project).

Check your installation:

```bash
java -version
mvn -version
git --version
```

## Run the project

Open a terminal in the project root folder.

Run tests:

```bash
mvn test
```

Compile and launch:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.portfolio.spam.Main"
```

If Maven Exec Plugin is not available in your local setup, use this dependency-free launch command after compiling:

**Windows PowerShell**
```powershell
java -cp target/classes com.portfolio.spam.Main
```

**macOS / Linux**
```bash
java -cp target/classes com.portfolio.spam.Main
```

Try these messages:

```text
Congratulations! Claim your free prize now
Can you send me the meeting notes?
Your account won a cash reward. Click here.
I will see you at the library tomorrow.
```

Type `exit` to stop.

If you run the Java command from a different folder, pass the CSV path:

```bash
java -cp target/classes com.portfolio.spam.Main /path/to/messages.csv
```

## How it works

1. **Load data:** Each CSV row contains a label (`spam` or `ham`) and a message.
2. **Preprocess:** Text is lowercased and split into alphabetic tokens.
3. **TF-IDF:** Each message becomes a numeric vector. TF measures a word's frequency in a message; IDF reduces the weight of words that appear in many messages.
4. **Train:** Naive Bayes learns how strongly each feature is associated with each class.
5. **Predict:** The model calculates a score for each class and returns the class with the highest score.

## Dataset

The included CSV is a tiny, hand-created teaching dataset. It is deliberately small so the project is easy to inspect. Do not present its predictions as production-grade accuracy. A real project should use a larger, licensed, representative dataset and a held-out test set.

## Limitations and future improvements

- Add a larger public SMS spam dataset and document its license/source.
- Create a stratified train/test split and report precision, recall, F1-score, and a confusion matrix.
- Add URL, phone-number, and sender metadata features.
- Save and load the trained model.
- Build a REST API with Spring Boot.
- Add a web interface.
- Compare Naive Bayes with Logistic Regression or a Java ML library such as Tribuo or Weka.

## Publish to GitHub

1. Create a new empty GitHub repository named `java-spam-message-detector`.
2. Do not initialize it with a README if you already have this project locally.
3. In the project root, run:

```bash
git init
git add .
git commit -m "Build Java spam message detector"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/java-spam-message-detector.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. Never commit passwords, API keys, or private data.

## Resume description

**Java Spam Message Detector | Java 17, Maven, Machine Learning**

Built a text classification application using TF-IDF feature extraction and a Multinomial Naive Bayes classifier. Implemented text preprocessing, CSV dataset loading, model training, interactive prediction, and automated tests in Java.

Only claim features you have run, tested, and can explain.
