package br.com.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.api.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
