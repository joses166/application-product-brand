package br.com.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.api.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
