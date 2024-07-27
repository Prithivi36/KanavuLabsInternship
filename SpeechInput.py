import speech_recognition as sr

def get_audio_input():
    recognizer = sr.Recognizer()

    with sr.Microphone() as source:
        print("Please say something...")
        
        audio = recognizer.listen(source)

        try:
            text = recognizer.recognize_google(audio)
            return text
        except sr.UnknownValueError:
            print("Sorry, I could not understand the audio.")
            return None
        except sr.RequestError:
            print("Sorry, I could not request results from the service.")
            return None


