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
    public ResponseEntity<CommonResponse> getRouteById(@PathVariable Integer route_id) {
        return ResponseEntity.ok(routeService.getRouteById(route_id));
    }

    @PostMapping
    public ResponseEntity<CommonResponse> addRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.addRoute(route));
    }

    @DeleteMapping("/delete/{route_id}")
    public ResponseEntity<CommonResponse> deleteRoute(@PathVariable Integer route_id) {
        return ResponseEntity.ok(routeService.deleteRouteById(route_id));
    }

    @PatchMapping("/update/{route_id}")
    public ResponseEntity<CommonResponse> updateRoute(@PathVariable Integer route_id, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.updateRouteById(route_id, route));
    }
}
