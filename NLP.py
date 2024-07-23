import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import make_pipeline
from sklearn.metrics import classification_report, confusion_matrix
import numpy as np
from Responses import responses
import random
import re

data = pd.read_csv('train.csv', sep=";")  
print(data.head())

data_test=pd.read_csv('test.csv', sep=";")

X_train = data['text']
y_train = data['intent']
X_test= data_test['text']
y_test= data_test['intent']

exit_words = ["exit", "quit", "bye", "goodbye", "stop"]
greet=["hi","hello","hola","hello there","hey","hey there","hi there","hello!","hi!","hey!","hey there!","hi there!","hello there!","hola!"]

model = make_pipeline(
    TfidfVectorizer(),
    MultinomialNB()
)

model.fit(X_train, y_train)

y_pred = model.predict(X_test)
print(classification_report(y_test, y_pred))
print(confusion_matrix(y_test, y_pred))

known_intents = y_train.unique()
print(known_intents)

def predict_intent(message, threshold=0.35):
    probabilities = model.predict_proba([message])[0]
    max_prob_index = np.argmax(probabilities)
    predicted_intent = model.classes_[max_prob_index]
    if probabilities[max_prob_index] >= threshold:
        return predicted_intent
    else:
        return "I'm not sure about that. Can you please elaborate?"

def bot_reply(message):
    intent = predict_intent(message)
    for(intents, response) in responses.items():
        if intents == intent:
            return np.random.choice(response)
    return np.random.choice(responses["default"])
common_questions = {
    "who are you": "I am a virtual assistant here to help you.",
    "what are you": "I am an AI chatbot designed to assist with your queries.",
    "where are you": "I exist in the digital world, here to assist you.",
    "what is your name": "You can call me your virtual assistant.",
    "how are you": "I'm just a program, but I'm here to help you!"
}
def handle_common_questions(message):
    message_lower = message.lower()
    for question, response in common_questions.items():
        if question in message_lower:
            return response
    return False

while True:
    rply = input("User : ")
    if any(word in rply for word in exit_words):
        print("BOT: Goodbye!")
        break
    if any(word in rply.lower() for word in greet):
        print(np.random.choice(responses["greetings"]))
    common_response = handle_common_questions(rply)
    if common_response:
        print(f"BOT: {common_response}")
        continue
    print("BOT :",bot_reply(rply))

