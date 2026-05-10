package org.brockhaus.supermarket.repository;

import org.brockhaus.supermarket.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p ORDER BY p.type")
    List<ProductEntity> findAllOrderByType();
}
