# -*- coding: utf-8 -*-
import requests 
import word as w

class Controller:
    def __init__(self, name, age):
        self.translator = "https://linguee-api.herokuapp.com/api"
        self.myServer = "http://localhost:8080/words"
        self.word = w.Word()

    def DeToEn(self, word):
        PARAMS = {'src':'de', 'dst':'en','q':word}
        response = requests.get(url = self.translator, params = PARAMS)
        if response != None:
            exactMatches = response["exact_matches"][0]
            enTranslation = exactMatches["translations"][0]["text"]
            return enTranslation


    def EnToRu(self, word):
        PARAMS = {'src':'en', 'dst':'ru','q':word}
        response = requests.get(url = self.translator, params = PARAMS) 
        if response != None:
            exactMatches = response["exact_matches"][0]
            enTranslation = exactMatches["translations"][0]["text"]
            return enTranslation


    def defineWordType(self):
        if wordType == "verb":
            self.word.isVerb(True)
        elif wordType == "adverb"