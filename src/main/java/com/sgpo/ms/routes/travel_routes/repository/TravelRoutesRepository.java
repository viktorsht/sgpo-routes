package com.sgpo.ms.routes.travel_routes.repository;

import com.sgpo.ms.routes.travel_routes.entities.TravelRoutes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRoutesRepository extends JpaRepository<TravelRoutes, Long> {
}
