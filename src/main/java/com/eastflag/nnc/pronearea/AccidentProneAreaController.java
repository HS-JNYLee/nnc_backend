package com.eastflag.nnc.pronearea;

import com.eastflag.nnc.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accident_prone_area")
public class AccidentProneAreaController {
    private final AccidentProneAreaService accidentProneAreaService;

    public AccidentProneAreaController(AccidentProneAreaService accidentProneAreaService) {
        this.accidentProneAreaService = accidentProneAreaService;
    }

    @GetMapping("/")
    public ResponseEntity<CommonResponse> findAll(@RequestParam String routeList) {
        return ResponseEntity.ok(accidentProneAreaService.findAll(routeList));
    }
}
