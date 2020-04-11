package com.ling.translator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ling.translator.model.Words;

public interface WordsRepo extends JpaRepository<Words, Long> {
	Words findById(int id);
	
	List<Words> findByIsNoun(boolean val);
	List<Words> findByIsPronoun(boolean val);
	List<Words> findByIsVerb(boolean val);
	List<Words> findByIsAdverb(boolean val);
	List<Words> findByIsAdjective(boolean val);
	List<Words> findByIsOther(boolean val);
	
	@Query(value = "SELECT * from words where id!=?1 ORDER BY rand() LIMIT 3", nativeQuery = true)
	List<Words> findRandomWords(int wordId);
	
	@Query(value = "SELECT * from words where is_noun=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomNouns(int wordId);
	
	@Query(value = "SELECT * from words where is_pronoun=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomPronouns(int wordId);
	
	@Query(value = "SELECT * from words where is_verb=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomVerbs(int wordId);
	
	@Query(value = "SELECT * from words where is_adverb=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomAdverbs(int wordId);
	
	@Query(value = "SELECT * from words where is_adjective=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomAdjectives(int wordId);
	
	@Query(value = "SELECT * from words where is_other=true and id!=?1 ORDER BY rand() LIMIT 3",
			nativeQuery = true)
	List<Words> findRandomOthers(int wordId);
}
