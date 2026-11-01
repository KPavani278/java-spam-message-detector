# Interview Questions and Study Notes

Try answering each question in your own words before reading the notes.

## 1. What does this project do?
It classifies a text message as spam or ham (normal message) using a basic machine-learning pipeline implemented in Java.

## 2. What is text preprocessing?
It converts raw text into a consistent representation before feature extraction. This project lowercases text and extracts alphabetic tokens.

## 3. What is a feature vector?
A numeric representation of an example that a machine-learning algorithm can process. Each vocabulary term maps to a vector position.

## 4. What is TF-IDF?
Term frequency measures how often a term appears in a document. Inverse document frequency reduces the weight of terms that appear in many documents. Their product is the TF-IDF weight.

## 5. What is Naive Bayes?
A probabilistic classifier based on Bayes' theorem. It uses a simplifying conditional-independence assumption between features given the class.

## 6. What is Multinomial Naive Bayes?
A Naive Bayes variant commonly used for text classification. It models feature contributions by class. This project supplies non-negative TF-IDF weights; count features are another common choice.

## 7. Why use Laplace smoothing?
It prevents an unseen feature from receiving zero probability in a class, avoiding a zeroed-out class score.

## 8. Why use logarithms?
Multiplying many small probabilities can underflow. Logarithms turn multiplication into addition and improve numerical stability.

## 9. What is the difference between training and prediction?
Training learns vocabulary statistics and class/feature information from labeled examples. Prediction transforms a new message with the learned vocabulary and scores the possible classes.

## 10. How should the model be evaluated?
Use a separate held-out test set and report precision, recall, F1-score, and a confusion matrix. Avoid evaluating only on training examples.

## 11. What are the current limitations?
The bundled dataset is tiny and synthetic. The model has basic preprocessing and has not been validated on representative real-world data.

## 12. What would you improve next?
Use a larger licensed dataset, create a reproducible train/test split, add evaluation metrics, compare feature representations and models, and consider model persistence or a REST API.
