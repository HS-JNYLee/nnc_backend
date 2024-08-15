package com.eastflag.nnc.route;

import com.eastflag.nnc.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    private final RouteService routeService;
    public RouteController(RouteService routeService) {this.routeService = routeService;}

    @GetMapping("/{route_id}")
    public ResponseEntity<CommonResponse> getRouteById(@PathVariable("route_id") Integer routeId) {
        return ResponseEntity.ok(routeService.getRouteById(routeId));
    }

    @PostMapping
    public ResponseEntity<CommonResponse> addRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.addRoute(route));
    }

    @DeleteMapping("/delete/{route_id}")
    public ResponseEntity<CommonResponse> deleteRoute(@PathVariable("route_id") Integer routeId) {
        return ResponseEntity.ok(routeService.deleteRouteById(routeId));
    }

    @PatchMapping("/update/{route_id}")
    public ResponseEntity<CommonResponse> updateRoute(@PathVariable("route_id") Integer routeId, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.updateRouteById(routeId, route));
    }
}
