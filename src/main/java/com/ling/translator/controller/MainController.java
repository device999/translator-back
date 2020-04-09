package com.ling.translator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class MainController {
	
	@GetMapping
    public ResponseEntity<String> welcome(){
    	return ResponseEntity.ok().body("Hello My Friend");
    }


}
