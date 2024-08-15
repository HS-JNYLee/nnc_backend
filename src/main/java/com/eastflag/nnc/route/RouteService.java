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

    public CommonResponse getRouteById(Integer route_id){
        Optional<Route> route = routeRepository.findByRouteId(route_id);
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

    public CommonResponse deleteRouteById(Integer route_id){
        Optional<Route> route = routeRepository.deleteByRouteId(route_id);
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

    public CommonResponse updateRouteById(Integer route_id, Route route){
        Optional<Route> routeOptional = routeRepository.findByRouteId(route_id);
        if(routeOptional.isPresent())
            routeRepository.deleteByRouteId(route_id);
        routeRepository.save(route);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }
}
