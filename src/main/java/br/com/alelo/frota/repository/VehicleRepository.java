package br.com.alelo.frota.repository;

import br.com.alelo.frota.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "vehicle", collectionResourceRel = "vehicle")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findOneByPlate(@Param("plate") String plate);
    List<Vehicle> findByStatus(@Param("status") Boolean status);

}
