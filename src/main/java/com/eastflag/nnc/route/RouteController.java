package com.eastflag.nnc.route;

import com.eastflag.nnc.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    private final RouteService routeService;
    public RouteController(RouteService routeService) {this.routeService = routeService;}

    /**
     * routeId로 조회
     * @param routeId
     * @return
     */
    @GetMapping("/{route_id}")
    public ResponseEntity<CommonResponse> getRouteById(@PathVariable("route_id") Integer routeId) {
        return ResponseEntity.ok(routeService.getRouteById(routeId));
    }

    /**
     * route 추가
     * @param route
     * @return
     */
    @PostMapping
    public ResponseEntity<CommonResponse> addRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.addRoute(route));
    }

    /**
     * routeId로 삭제
     * @param routeId
     * @return
     */
    @DeleteMapping("/delete/{route_id}")
    public ResponseEntity<CommonResponse> deleteRoute(@PathVariable("route_id") Integer routeId) {
        return ResponseEntity.ok(routeService.deleteRouteById(routeId));
    }

    /**
     * 업데이트
     *
     * @param routeId
     * @param route
     * @return
     */
    @PatchMapping("/update/{route_id}")
    public ResponseEntity<CommonResponse> updateRoute(@PathVariable("route_id") Integer routeId, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.updateRouteById(routeId, route));
    }

    /**
     * route 삭제
     *
     * @param route
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<CommonResponse> deleteRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.deleteRoute(route));
    }
}
