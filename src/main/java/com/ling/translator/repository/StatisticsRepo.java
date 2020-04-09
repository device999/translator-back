package com.ling.translator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ling.translator.model.Statistics;

public interface StatisticsRepo extends JpaRepository<Statistics,Long> {
	Statistics findById(int id);
	
	Statistics findByWordId(int wordId);
	
	List<Statistics> findByUserId(int userId);
}
