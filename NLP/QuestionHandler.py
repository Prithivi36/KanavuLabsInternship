import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import make_pipeline
from sklearn.metrics import classification_report, confusion_matrix
import numpy as np
from YesorNo import yesOrNo;

data = pd.read_csv('Question.csv', sep=";")
print(data.head())

X_train, X_test, y_train, y_test = train_test_split(
    data['questions'], data['type'], test_size=0.2, random_state=42
)

model = make_pipeline(
    TfidfVectorizer(),
    MultinomialNB()
)
model.fit(X_train, y_train)

y_pred = model.predict(X_test)
print(classification_report(y_test, y_pred))
print(confusion_matrix(y_test, y_pred))

def predict_intent(message, threshold=0.3):
    if any(word in message for word in ["what", "why", "have", "did", "stop"]):
        return "general"
    probabilities = model.predict_proba([message])[0]
    print(probabilities)
    max_prob_index = np.argmax(probabilities)
    predicted_intent = model.classes_[max_prob_index]
    if probabilities[max_prob_index] >= threshold:
        return predicted_intent
    else:
        return "general"

def bot_reply_ques(message):
    intent = predict_intent(message)
    if(intent in ["general", "doubtful"]):
        return np.random.choice(yesOrNo[intent])
