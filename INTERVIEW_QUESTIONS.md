# Interview Questions and Sample Answers

Use these to practice explaining your own implementation. Adapt the answers to what you have actually built and tested.

## 1. What does your project do?
It classifies a text message into spam or ham. It preprocesses the message, converts it to TF-IDF features, and uses a Naive Bayes classifier to choose a class.

## 2. Is this really machine learning?
Yes. The classifier estimates class and feature probabilities from labeled examples during training, then uses the learned values to predict labels for new messages. The text vectorizer also learns its vocabulary and IDF weights from the training documents.

## 3. What is supervised learning?
Supervised learning trains a model using examples that contain both inputs and expected outputs. Here, the input is a message and the output label is spam or ham.

## 4. Why did you choose Naive Bayes?
It is a common, efficient baseline for text classification. It is relatively simple to understand and train, which makes it useful for a first machine-learning project. It is not guaranteed to be the best model.

## 5. What is TF-IDF?
TF-IDF gives a word a higher weight when it is frequent in one document but not common across all documents. It helps represent the importance of words in a text collection.

## 6. What is Laplace smoothing?
It adds a small positive value to feature counts so an unseen feature does not produce a zero probability. This implementation uses alpha = 1.

## 7. What is the difference between training and prediction?
Training learns model parameters from labeled examples. Prediction uses those learned parameters to classify a new message.

## 8. How would you evaluate the model?
I would separate data into training and test sets, train only on the training set, and evaluate predictions on unseen test examples. I would report precision, recall, F1-score, and a confusion matrix, not just accuracy.

## 9. What are false positives and false negatives?
A false positive is a normal message predicted as spam. A false negative is a spam message predicted as normal.

## 10. What are the limitations?
The included dataset is small and synthetic. The model can overfit its vocabulary, does not understand context like a human, and may fail on new wording, languages, misspellings, or changing spam tactics.

## 11. How could you improve it?
Use a larger representative dataset, create a stratified test split, measure precision/recall/F1, tune preprocessing and smoothing, compare other classifiers, and deploy it behind a REST API.

## 12. Why use Java for machine learning?
Java is useful for building maintainable applications and production services. Libraries such as Weka and Tribuo provide machine-learning capabilities, and Spring Boot can expose a model through an API.

## 30-second project explanation
"I built a Java-based spam message classifier. It loads labeled text examples from a CSV file, tokenizes the messages, converts them into TF-IDF feature vectors, and trains a Multinomial Naive Bayes classifier. Users can enter a message in the terminal and receive a spam or ham prediction. I also added automated tests. The current dataset is small, so my next step is to evaluate it on a larger held-out dataset and report precision, recall, and F1-score."
