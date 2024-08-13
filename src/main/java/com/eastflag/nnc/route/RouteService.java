package com.eastflag.nnc.route;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public CommonResponse getRouteById(int route_id){
        Optional<Route> route = routeRepository.findRouteById(route_id);
        String message = null;
        if (route.isPresent())
            message = ResponseMessage.SUCCESS;
        else
            message = "Route : " + route_id + " - does not exist";

        return CommonResponse.builder()
                .code(200)
                .message(message)
                .data(route)
                .build();
    }

    public CommonResponse addRoute(Route route){
        routeRepository.save(route);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }

    public CommonResponse deleteRouteById(int route_id){
        Optional<Route> route = routeRepository.deleteRouteById(route_id);
        String message = "";
        if (route.isPresent())
            message = ResponseMessage.SUCCESS;
        else
            message = "Route : " + route_id + " - does not exist";
        return CommonResponse.builder()
                .code(200)
                .message(message)
                .data(route)
                .build();
    }

    public CommonResponse updateRouteById(int route_id, Route route){
        Optional<Route> routeOptional = routeRepository.findRouteById(route_id);
        if(routeOptional.isPresent())
            routeRepository.deleteRouteById(route_id);
        routeRepository.save(route);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }
}
