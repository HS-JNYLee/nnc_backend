package com.eastflag.nnc.route;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import retrofit2.http.Body;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByRouteId(@Param("route_id") Integer routeId);
    Optional<Route> deleteByRouteId(@Param("route_id") Integer routeId);
    void delete(@NotNull @Body Route route);
}
