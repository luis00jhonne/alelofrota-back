package br.com.alelo.frota.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.alelo.frota.model.Vehicle;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Page<Vehicle> findByPlate(String plate, Pageable pageable);
    Page<Vehicle> findByStatus(Boolean status, Pageable pageable);
    Optional<Vehicle> findOneByPlate(String plate);

}
