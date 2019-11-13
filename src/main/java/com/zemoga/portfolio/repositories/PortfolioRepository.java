package com.zemoga.portfolio.repositories;

import com.zemoga.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    @Query(value = "select next_val from hibernate_sequence;", nativeQuery = true)
    public Integer findNextPortfolioId();

}
