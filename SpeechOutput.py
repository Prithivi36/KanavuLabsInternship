import pyttsx3

def text_to_speech(text):
    engine = pyttsx3.init()
    
    engine.setProperty('rate', 150)  
    engine.setProperty('volume', 1.0)
    engine.setProperty('voice', "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Speech\Voices\Tokens\TTS_MS_EN-US_ZIRA_11.0")

    engine.say(text)
    
    engine.runAndWait()



