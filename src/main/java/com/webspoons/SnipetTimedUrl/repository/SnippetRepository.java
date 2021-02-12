package com.webspoons.SnipetTimedUrl.repository;

import com.webspoons.SnipetTimedUrl.model.SnippetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnippetRepository extends JpaRepository<SnippetModel, Long> {
    public Optional<SnippetModel> findByName(String name);
}
