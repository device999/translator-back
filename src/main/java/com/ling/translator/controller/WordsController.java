package com.ling.translator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ling.translator.model.Statistics;
import com.ling.translator.model.WordLight;
import com.ling.translator.model.Words;
import com.ling.translator.repository.StatisticsRepo;
import com.ling.translator.repository.WordsRepo;

@RestController
@RequestMapping(path = "/words")
public class WordsController {

	
	@Autowired
	private WordsRepo wordRepo;
	
	@Autowired
	private StatisticsRepo statRepo;
	
	@GetMapping
	public ResponseEntity<List<WordLight>> getQuizz(){
		List<Words> allWords = wordRepo.findAll();
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomWords(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Words> getParticularWord(@PathVariable(value = "id") int id){
		Words word = wordRepo.findById(id);
		return ResponseEntity.ok().body(word);
	}
	

	
	@GetMapping("/all")
	public ResponseEntity<List<Words>> getAllWords(){
		List<Words> allWords = wordRepo.findAll();
		return ResponseEntity.ok().body(allWords);
	}
	
	@PostMapping
	public ResponseEntity<Words> postWord(@RequestBody Words newWord){
		wordRepo.save(newWord);
		Statistics newStat = new Statistics();
		newStat.setUserId(1);
		newStat.setWordId(newWord.getId());
		statRepo.save(newStat);
		return ResponseEntity.status(HttpStatus.CREATED).body(newWord);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Words> updateWord(@PathVariable(value = "id") int id,
			@RequestBody Words newWord){
		Words oldWord = wordRepo.findById(id);
		oldWord.setRussian(newWord.getRussian());
		oldWord.setGerman(newWord.getGerman());
		oldWord.setAdjective(newWord.isAdjective());
		oldWord.setNoun(newWord.isNoun());
		oldWord.setVerb(newWord.isVerb());
		oldWord.setAdverb(newWord.isAdverb());
		oldWord.setPronoun(newWord.isPronoun());
		oldWord.setOther(newWord.isOther());
		wordRepo.save(oldWord);
		return ResponseEntity.status(HttpStatus.OK).body(newWord);
	}
	
	@GetMapping("/nouns")
	public ResponseEntity<List<WordLight>> getAllNouns(){
		List<Words> allWords = wordRepo.findByIsNoun(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomNouns(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/verbs")
	public ResponseEntity<List<WordLight>> getAllVerbs(){
		List<Words> allWords = wordRepo.findByIsVerb(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomVerbs(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/pronouns")
	public ResponseEntity<List<WordLight>> getAllPronouns(){
		List<Words> allWords = wordRepo.findByIsPronoun(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomPronouns(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	@GetMapping("/others")
	public ResponseEntity<List<WordLight>> getAllOther(){
		List<Words> allWords = wordRepo.findByIsOther(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomOthers(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/adverbs")
	public ResponseEntity<List<WordLight>> getAllAdverbs(){
		List<Words> allWords = wordRepo.findByIsAdverb(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomAdverbs(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/adjectives")
	public ResponseEntity<List<WordLight>> getAllAdjectives(){
		List<Words> allWords = wordRepo.findByIsAdjective(true);
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Words word: allWords) {
			List<Words> backupWords = wordRepo.findRandomAdjectives(word.getId());
			WordLight lightWord = quizLoad(word, backupWords);
			lightWords.add(lightWord);
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	@GetMapping("/mistakes")
	public ResponseEntity<List<WordLight>> workOnMistakes(){
		List<Statistics> allStats = statRepo.findAll();
		List<WordLight> lightWords = new ArrayList<WordLight>();
		for(Statistics currStat: allStats) {
			float attempts = 0;
			float threshold = 0;
			if(currStat.getCorrectAnswer() + currStat.getWrongAnswer() > 0) {
				attempts = currStat.getCorrectAnswer() + currStat.getWrongAnswer();
				threshold = (currStat.getCorrectAnswer() * 100) / attempts;
				if(threshold < 70) {
					Words getWord = wordRepo.findById(currStat.getWordId());
					WordLight lightWord = quizLoad(getWord, this.generateWrongAnswers(getWord));
					lightWords.add(lightWord);
				}
			}else {
				Words getWord = wordRepo.findById(currStat.getWordId());
				WordLight lightWord = quizLoad(getWord, this.generateWrongAnswers(getWord));
				lightWords.add(lightWord);
			}
		}
		Collections.shuffle(lightWords);
		return ResponseEntity.ok().body(lightWords);
	}
	
	private List<Words> generateWrongAnswers(Words word){
		if(word.isNoun()){
			return wordRepo.findRandomNouns(word.getId());
		}
		else if(word.isVerb()) {
			return wordRepo.findRandomVerbs(word.getId());
		}
		else if(word.isAdjective()) {
			return wordRepo.findRandomAdjectives(word.getId());
		}
		else if(word.isAdverb()) {
			return wordRepo.findRandomAdverbs(word.getId());
		}
		else if(word.isPronoun()) {
			return wordRepo.findRandomPronouns(word.getId());
		}
		else {
			return wordRepo.findRandomOthers(word.getId());
		}
	}
	
    public WordLight quizLoad(Words requiredWord, List < Words > backupWrong) {
        WordLight lightWord = new WordLight();
        lightWord.setId(requiredWord.getId());
        if (requiredWord.getId() % 2 == 0) {
            lightWord.setWord(requiredWord.getRussian());
            lightWord.setCorrectAnswer(requiredWord.getGerman());
            String[] wrongs = {
                backupWrong.get(0).getGerman(),
                backupWrong.get(1).getGerman(),
                backupWrong.get(2).getGerman()
            };
            lightWord.setWrongAnswers(wrongs);
        } else {
            lightWord.setWord(requiredWord.getGerman());
            lightWord.setCorrectAnswer(requiredWord.getRussian());
            String[] wrongs = {
                backupWrong.get(0).getRussian(),
                backupWrong.get(1).getRussian(),
                backupWrong.get(2).getRussian()
            };
            lightWord.setWrongAnswers(wrongs);
        }
        return lightWord;
    }
}
