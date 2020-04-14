package com.ling.translator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ling.translator.model.StatisticLight;
import com.ling.translator.model.Statistics;
import com.ling.translator.model.Words;
import com.ling.translator.repository.StatisticsRepo;
import com.ling.translator.repository.WordsRepo;

@RestController
@RequestMapping(path = "/stats")
public class StatisticController {
	
	@Autowired
	private StatisticsRepo statRepo;
	
	@Autowired
	private WordsRepo wordRepo;
	
	/**@GetMapping
    public ResponseEntity<Statistics> getUserStats(){
		Statistics stat = statRepo.findById(1);		
    	return ResponseEntity.ok().body(stat);
    }
    **/
	
	@GetMapping
    public ResponseEntity<List<StatisticLight>> getUserStats(){
		List<Statistics> stats = statRepo.findByUserId(1);
		List<StatisticLight> lightVersion = new ArrayList<StatisticLight>();
		for(Statistics stat: stats) {
			Words word = wordRepo.findById(stat.getWordId());
			StatisticLight newLight = new StatisticLight();
			newLight.setId(stat.getId());
			newLight.setWord(word.getGerman());
			newLight.setCorrects(stat.getCorrectAnswer());
			newLight.setWrongs(stat.getWrongAnswer());
			newLight.setWordType(this.mapWordType(word));
			lightVersion.add(newLight);
		}
    	return ResponseEntity.ok().body(lightVersion);
    }
	
	private String mapWordType(Words word){
		if(word.isNoun()){return "NOUN";}
		else if(word.isVerb()) {return "VERB";}
		else if(word.isAdjective()) {return "ADJECTIVE";}
		else if(word.isAdverb()) {return "ADVERB";}
		else if(word.isPronoun()) {return "PRONOUN";}
		else {return "OTHER";}
	}
	
	
	
	
	@PostMapping
    public ResponseEntity<Statistics> loadUser(@RequestBody Statistics stats){
		statRepo.save(stats);
		return ResponseEntity.status(HttpStatus.CREATED).body(stats);
    }
	
	@GetMapping("/{wordId}/wrong")
    public ResponseEntity<Statistics> wrongGuess(@PathVariable(value = "wordId") int wordId){
		Statistics loadedStatistic = statRepo.findByWordId(wordId);
		if(loadedStatistic==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		int wrongGuess = loadedStatistic.getWrongAnswer() + 1;
		loadedStatistic.setWrongAnswer(wrongGuess);
		statRepo.save(loadedStatistic);
		return ResponseEntity.status(HttpStatus.OK).body(loadedStatistic);
    }
	
	@GetMapping("/{wordId}/correct")
    public ResponseEntity<Statistics> correctQuess(@PathVariable(value = "wordId") int wordId){

		Statistics loadedStatistic = statRepo.findByWordId(wordId);
		if(loadedStatistic==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		int correctQuess = loadedStatistic.getCorrectAnswer() + 1;
		loadedStatistic.setCorrectAnswer(correctQuess);
		statRepo.save(loadedStatistic);
		return ResponseEntity.status(HttpStatus.OK).body(loadedStatistic);
    }

}
