# -*- coding: utf-8 -*-

class Word:

    def __init__(self):
        self.isNoun = False;
        self.isVerb = False;
        self.isAdverb = False;
        self.isPronoun = False;
        self.isAdjective = False;
        self.isOther = False;
        self.russian = "";
        self.german = "";
        
    def setRussian(self, word):
        self.russian = word
    
    def setGerman(self, word):
        self.german = word
    
    def setIsNoun(self, noun):
        self.isNoun = noun
    
    def setIsVerb(self, verb):
        self.isVerb = verb
        
    def setIsPronoun(self, pronoun):
        self.isPronoun = pronoun
    
    def setIsAdjective(self, adj):
        self.isAdjective = adj
        
    def setIsOther(self, other):
        self.isOther = other

    def setIsAdverb(self, adverb):
        self.isAdverb = adverb

