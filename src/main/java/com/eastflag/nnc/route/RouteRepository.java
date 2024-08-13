package com.eastflag.nnc.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findRouteById(@Param("route_id") Integer route_id);
    Optional<Route> deleteRouteById(@Param("route_id") Integer route_id);
}
