package com.example.viewupdateexample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.viewupdateexample.model.ViewCount;
import com.example.viewupdateexample.repository.ViewCountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewCountService {

	private final ViewCountRepository viewCountRepository;

	public void increaseViewCount(Long id) {
		viewCountRepository.increaseViewCount(id);
	}

	public void increaseViewCountV2(Long id) {
		ViewCount viewCount = viewCountRepository.findById(id)
			.orElseThrow();

		viewCount.incrementCount();
	}

	public Long getCount(Long id) {
		return viewCountRepository.findById(id)
			.orElseThrow()
			.getCount();
	}

}
