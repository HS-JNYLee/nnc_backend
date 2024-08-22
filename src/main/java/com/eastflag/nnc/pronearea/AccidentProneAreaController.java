package com.eastflag.nnc.pronearea;

import com.eastflag.nnc.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accident_prone_area")
public class AccidentProneAreaController {
    private final AccidentProneAreaService accidentProneAreaService;

    public AccidentProneAreaController(AccidentProneAreaService accidentProneAreaService) {
        this.accidentProneAreaService = accidentProneAreaService;
    }

    @PostMapping("/")
    public ResponseEntity<CommonResponse> findAll(@RequestBody String query) {
        return ResponseEntity.ok(accidentProneAreaService.findAll(query));
    }
}
