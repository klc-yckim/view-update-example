package com.example.viewupdateexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.viewupdateexample.model.ViewCount;

public interface ViewCountRepository extends JpaRepository<ViewCount, Long>, QuerydslViewCountRepository {

}
