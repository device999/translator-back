package com.ling.translator.controller;

import java.util.ArrayList;
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
		List<WordLight> response= quizLoad(allWords);
		return ResponseEntity.ok().body(response);
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
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	
	@GetMapping("/verbs")
	public ResponseEntity<List<WordLight>> getAllVerbs(){
		List<Words> allWords = wordRepo.findByIsVerb(true);
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	
	@GetMapping("/pronouns")
	public ResponseEntity<List<WordLight>> getAllPronouns(){
		List<Words> allWords = wordRepo.findByIsPronoun(true);
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	@GetMapping("/others")
	public ResponseEntity<List<WordLight>> getAllOther(){
		List<Words> allWords = wordRepo.findByIsOther(true);
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	
	@GetMapping("/adverbs")
	public ResponseEntity<List<WordLight>> getAllAdverbs(){
		List<Words> allWords = wordRepo.findByIsAdverb(true);
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	
	@GetMapping("/adjectives")
	public ResponseEntity<List<WordLight>> getAllAdjectives(){
		List<Words> allWords = wordRepo.findByIsAdjective(true);
		return ResponseEntity.ok().body(quizLoad(allWords));
	}
	
	public List<WordLight> quizLoad(List<Words> results){
		List<WordLight> lightWords = new ArrayList();		
		for(Words word: results) {
			WordLight lightWord = new WordLight();
			List<Words> allWords = wordRepo.findRandomWords(word.getId());
			int size = allWords.size();
			lightWord.setId(word.getId());
			if(word.getId() % 2 == 0) {
				lightWord.setWord(word.getRussian());
				lightWord.setCorrectAnswer(word.getGerman());
				String[] wrongs = {allWords.get(0).getGerman(),
						allWords.get(1).getGerman(),
						allWords.get(2).getGerman()};
				lightWord.setWrongAnswers(wrongs);
			}
			else {
				lightWord.setWord(word.getGerman());
				lightWord.setCorrectAnswer(word.getRussian());
				String[] wrongs = {allWords.get(0).getRussian(),
						allWords.get(1).getRussian(),
						allWords.get(2).getRussian()};
				lightWord.setWrongAnswers(wrongs);
			}
			lightWords.add(lightWord);
		}
		return lightWords;
	}
}
