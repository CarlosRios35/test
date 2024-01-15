package com.between.challenge.domain.repository;

import com.between.challenge.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM prices WHERE company_id=:companyId AND product_id=:productId AND :date between start_date AND end_date")
    List<Prices> findAllByDate(Integer companyId, Integer productId, Date date);
}


