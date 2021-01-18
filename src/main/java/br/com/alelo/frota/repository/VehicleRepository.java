package br.com.alelo.frota.repository;

import br.com.alelo.frota.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findOneByPlate(String plate);
    Page<Vehicle> findByStatus(Boolean status, Pageable pageable);

}
