package com.eastflag.nnc.testkmj.relation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 유저 관계 Service 클래스
 *
 */
@Service
@RequiredArgsConstructor
public class UserRelationService {
    private final UserRelationRepository userRelationRepository;

    /**
     * 유저 관계 Entity를 DataBase에 생성하는 함수
     *
     * @param caretakerId 피보호자가 될 Id
     * @param caregiverId 보호자가 될 Id
     * @param relation 피보호자에 대한 보호자의 관계
     * @return 생성된 유저 관계 Entity
     */
    public UserRelation createUserRelation(int caretakerId, int caregiverId, String relation) {
        var userRelation = UserRelation.builder()
                .caretakerId(caretakerId)
                .caregiverId(caregiverId)
                .relation(relation)
                .build();

        userRelationRepository.save(userRelation);

        return userRelation;
    }

    /**
     * 유저 관계 Entity 데이터를 변경하는 함수
     * ※ 관계를 변경시키는 주체는 무조건 CareTaker이다.
     *
     * @param request UserRelationController.updateUserRelation에서 가져온 정보
     */
    public UserRelation updateUserRelation(UpdateUserRelationRequest request) {
        var userRelation = userRelationRepository
                .findByCaretakerId(request.getCaretakerId())
                .orElseThrow(() -> new RuntimeException(request.getCaretakerId() + "를 찾을 수 없음."));

        userRelation.setCaregiverId(request.getCaregiverId());
        userRelation.setRelation(request.getRelation());

        userRelationRepository.save(userRelation);

        return userRelation;
    }
}
