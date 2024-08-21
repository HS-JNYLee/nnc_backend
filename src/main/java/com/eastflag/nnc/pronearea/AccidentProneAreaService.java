package com.eastflag.nnc.pronearea;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentProneAreaService {
    private final AccidentProneAreaRepository accidentProneAreaRepository;
    private final Gson gson = new Gson();
    @Autowired
    public AccidentProneAreaService(AccidentProneAreaRepository accidentProneAreaRepository) {
        this.accidentProneAreaRepository = accidentProneAreaRepository;
    }

    public CommonResponse findAll(String routeList) {
        List<AccidentProneArea> accidentProneAreas = accidentProneAreaRepository.findAll();
        List<Coordinate> coordinates = gson.fromJson(routeList, List.class);

        ArrayList<AccidentProneArea> result = new ArrayList<>();
        for(Coordinate coordinate : coordinates) {
            for(AccidentProneArea accidentProneArea : accidentProneAreas) {
                double distance = DistanceCalculator.calculate(coordinate.getLongitude(),coordinate.getLatitude(),
                        accidentProneArea.getLongitude(), accidentProneArea.getLatitude()) * 1000;

                if(distance < 30 && !result.contains(accidentProneArea)){
                    result.add(accidentProneArea);
                }
            }
        }

        return CommonResponse.builder()
            .code(200)
            .message(ResponseMessage.SUCCESS)
            .data(result)
            .build();
    }
}
