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
from Comforting import comforts
from YesorNo import yesOrNo
from SpeechInput import get_audio_input
from SpeechOutput import text_to_speech
from ideas import ideas_list
from QuestionHandler import predict_intent, bot_reply_ques

data = pd.read_csv('train.csv', sep=";")  
print(data.head())

data_test=pd.read_csv('test.csv', sep=";")

X_train = data['text']
y_train = data['intent']
X_test= data_test['text']
y_test= data_test['intent']

exit_words = ["exit", "quit", "bye", "goodbye"]
greet=["hi","hello","hola","hey","hi chatbot","hello chatbot","hola chatbot","hey chatbot"]

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
    print(predicted_intent)
    print(probabilities)
    if probabilities[max_prob_index] >= threshold:
        return predicted_intent
    else:
        return "I'm not sure about that. Can you please elaborate?"
reason_found=False
def bot_reply(message):
    global reason_found
    
    intent = predict_intent(message)
    if(reason_found):
        for(intents,comfort) in comforts.items():
            if intents == intent:
                resp= np.random.choice(comfort)
                ideation=np.random.choice(ideas_list[intents])
                resp=resp+"\n"+ideation
                return resp

    for(intents, response) in responses.items():
        if intents == intent:
            reason_found=True
            resp= np.random.choice(response)
            return resp
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

def is_question(sentence):
    
    sentence = sentence.lower()
    
    question_patterns = [
        r'^who\b',
        r'^you sure\b',
        r'^youre sure\b',
        r'^what\b',
        r'^where\b',
        r'^when\b',
        r'^why\b',
        r'^how do\b',
        r'^how are\b',
        r'^how did\b',
        r'^how does\b',
        r'^can\b',
        r'^do\b',
        r'^did\b',
        r'^will\b',
        r'^would\b',
        r'^should\b',
        r'^could\b',
        r'^is\b',
        r'^are\b',
        r'^do\b',
        r'^does\b',
        r'^can\b',
        r'^could\b',
        r'^will\b',
        r'^would\b',
        r'^shall\b',
        r'^should\b',
        r'^has\b',
        r'^have\b',
        r'^had\b',
        r'^was\b',
        r'^were\b'
    ]
    
    for pattern in question_patterns:
        if re.search(pattern, sentence):
            return True

    return False


while True:
    rply = get_audio_input()
    print("User :",rply)
    # rply = input("User: ")
    # print("User :",rply)
    if rply is None:
        continue
    if(is_question(rply)):
        bot_rply=bot_reply_ques(rply)
        text_to_speech(bot_rply)
        print("BOT :",bot_rply)
        continue
    if any(word in rply for word in exit_words):
        text_to_speech("Goodbye!")
        print("BOT: Goodbye!")
        break
    if any(re.fullmatch(r'\b{}\b'.format(word), rply.lower()) for word in greet):
        randomz = np.random.choice(responses["greetings"])
        text_to_speech(randomz)
        print(f"BOT: {randomz}")
        continue
    common_response = handle_common_questions(rply)
    if common_response:
        text_to_speech(common_response)
        print(f"BOT: {common_response}")
        continue
    bot_rply=bot_reply(rply)
    text_to_speech(bot_rply)
    print("BOT :",bot_rply)