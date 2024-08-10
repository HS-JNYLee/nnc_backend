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
}
