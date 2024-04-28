package com.example.viewupdateexample.repository;

import static com.example.viewupdateexample.model.QViewCount.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerydslViewCountRepositoryImpl
	implements QuerydslViewCountRepository {

	private final JPAQueryFactory query;

	@Override
	public void increaseViewCount(Long id) {
		query
			.update(viewCount)
			.set(viewCount.count, viewCount.count.add(1))
			.where(
				viewCount.id.eq(id)
			)
			.execute();
	}
}
