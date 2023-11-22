package kz.bitlab.middle01reids.middle01reids.repository;

import kz.bitlab.middle01reids.middle01reids.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
