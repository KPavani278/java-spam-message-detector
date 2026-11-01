package com.portfolio.spam;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpamDetectorTest {
    @Test
    void learnsToRecognizeRepresentativeSpamAndNormalMessages() {
        List<Message> trainingData = List.of(
                new Message("ham", "Please call me after the meeting"),
                new Message("ham", "Your class starts tomorrow morning"),
                new Message("ham", "I sent the project report"),
                new Message("ham", "Are we meeting at the library"),
                new Message("spam", "Win a free cash prize click now"),
                new Message("spam", "Claim your free reward immediately"),
                new Message("spam", "Congratulations you won a prize"),
                new Message("spam", "Get cash bonus now click here")
        );

        SpamDetector detector = new SpamDetector();
        detector.train(trainingData);

        assertEquals("spam", detector.predict("Claim your free prize now"));
        assertEquals("ham", detector.predict("Please send the project report tomorrow"));
    }

    @Test
    void rejectsPredictionBeforeTraining() {
        SpamDetector detector = new SpamDetector();
        assertThrows(IllegalStateException.class, () -> detector.predict("hello"));
    }
}
