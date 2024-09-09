package com.eastflag.nnc.route;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    /**
     * 경로 정보 조회
     * @param route_id
     * @return
     */
    public CommonResponse getRouteById(Integer route_id){
        Optional<Route> route = routeRepository.findByRouteId(route_id);
        String message = null;
        if (route.isPresent())
            message = ResponseMessage.SUCCESS;
        else {
            message = "Route : " + route_id + " - does not exist";
            log.info(message);
        }

        return CommonResponse.builder()
                .code(200)
                .message(message)
                .data(route)
                .build();
    }

    /**
     * 경로 추가
     * @param route
     * @return
     */
    public CommonResponse addRoute(Route route){
        routeRepository.save(route);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }

    /**
     * 경로 삭제
     * @param route_id
     * @return
     */
    @Transactional
    public CommonResponse deleteRouteById(Integer route_id){
        routeRepository.deleteByRouteId(route_id);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .build();
    }

    /**
     * 경로 삭제
     * @param route
     * @return
     */
    public CommonResponse deleteRoute(Route route){
        routeRepository.delete(route);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }

    /**
     * 경로 업데이트
     *
     * @param route_id
     * @param route
     * @return
     */
    public CommonResponse updateRouteById(Integer route_id, Route route){
        Optional<Route> originalRoute = routeRepository.findByRouteId(route_id);
        // 이미 존재하는 경우
        if(originalRoute.isPresent()){
            originalRoute.get().setLocation(route.getLocation());
            originalRoute.get().setCron(route.getCron());
            routeRepository.save(route);
        }
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(route)
                .build();
    }
}
