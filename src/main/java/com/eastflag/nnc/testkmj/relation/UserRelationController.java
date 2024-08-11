package com.eastflag.nnc.testkmj.relation;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * UserRelation과 관련된 API가 관리되는 Controller
 *
 */
@RestController
@RequestMapping("/api/v1/userRelation")
@RequiredArgsConstructor
public class UserRelationController {
    private final UserRelationRepository userRelationRepository;
    private final UserRelationService userRelationService;

    /**
     * UserRelation 정보를 전달한다.
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
     */
    @GetMapping("/getUserRelation/{userId}")
    public CommonResponse getUserRelation(
            @PathVariable int userId
    ) {
        var userRelation = userRelationRepository.findByCaretakerId(userId).orElseGet(() -> userRelationRepository
                .findByCaregiverId(userId)
                .orElseThrow(() -> new RuntimeException(userId + "에 대한 관계 설정이 없음")));
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userRelation).build();
    }

    //TODO: 사용자가 보호자 변경하는 것
    @PatchMapping("/updateUserRelation")
    public CommonResponse updateUserRelation(
            @RequestBody UpdateUserRelationRequest request
    ){
        var userRelation = userRelationService.updateUserRelation(request);

        return CommonResponse.builder().code(200).message(request.getCaretakerId() + ": 객제 전달 성공").data(userRelation).build();
    }

    //TODO: 관계는 사용자 계정이 삭제될 때 작동
}
