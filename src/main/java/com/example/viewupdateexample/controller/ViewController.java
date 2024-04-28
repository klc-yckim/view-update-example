package com.example.viewupdateexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.viewupdateexample.service.ViewCountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewController {

	private final ViewCountService viewCountService;

	@PostMapping("/views/{id}")
	public ResponseEntity<Void> increaseViewCount(
		@PathVariable Long id
	) {
		viewCountService.increaseViewCount(id);

		return ResponseEntity.status(HttpStatus.OK)
			.build();
	}
}
