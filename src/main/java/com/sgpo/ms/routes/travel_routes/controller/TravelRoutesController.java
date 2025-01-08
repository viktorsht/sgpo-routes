package com.sgpo.ms.routes.travel_routes.controller;

import com.sgpo.ms.routes.travel_routes.entities.TravelRoutes;
import com.sgpo.ms.routes.travel_routes.repository.TravelRoutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class TravelRoutesController {

    @Autowired
    private TravelRoutesRepository travelRoutesRepository;

    // Endpoint GET - Retorna todas as rotas
    @GetMapping
    public ResponseEntity<List<TravelRoutes>> getAllRoutes() {
        List<TravelRoutes> routes = travelRoutesRepository.findAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelRoutes> getRouteById(@PathVariable Long id) {
        // Busca a rota no repositório pelo ID
        return travelRoutesRepository.findById(id)
                .map(ResponseEntity::ok) // Se encontrada, retorna o objeto TravelRoutes com status 200 (OK)
                .orElse(ResponseEntity.notFound().build()); // Se não encontrada, retorna status 404 (Not Found)
    }


    // Endpoint POST - Cria uma nova rota
    @PostMapping
    public ResponseEntity<TravelRoutes> createRoute(@RequestBody TravelRoutes travelRoute) {
        TravelRoutes savedRoute = travelRoutesRepository.save(travelRoute);
        return ResponseEntity.ok(savedRoute);
    }

    // Endpoint PUT - Atualiza uma rota existente
    @PutMapping("/{id}")
    public ResponseEntity<TravelRoutes> updateRoute(@PathVariable Long id, @RequestBody TravelRoutes travelRoute) {
        Optional<TravelRoutes> existingRoute = travelRoutesRepository.findById(id);
        if (existingRoute.isPresent()) {
            TravelRoutes updatedRoute = existingRoute.get();
            updatedRoute.setRouteName(travelRoute.getRouteName());
            updatedRoute.setDepartureLocation(travelRoute.getDepartureLocation());
            updatedRoute.setArrivalLocation(travelRoute.getArrivalLocation());
            updatedRoute.setDepartureTime(travelRoute.getDepartureTime());
            updatedRoute.setPrice(travelRoute.getPrice());
            TravelRoutes savedRoute = travelRoutesRepository.save(updatedRoute);
            return ResponseEntity.ok(savedRoute);
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 se a rota não for encontrada
        }
    }

    // Endpoint DELETE - Deleta uma rota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        Optional<TravelRoutes> existingRoute = travelRoutesRepository.findById(id);
        if (existingRoute.isPresent()) {
            travelRoutesRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // Retorna 204 se a exclusão for bem-sucedida
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 se a rota não for encontrada
        }
    }
}
