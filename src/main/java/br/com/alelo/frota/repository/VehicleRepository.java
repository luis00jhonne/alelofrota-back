package br.com.alelo.frota.repository;

import br.com.alelo.frota.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "vehicle", collectionResourceRel = "vehicle")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
