package com.eastflag.nnc.user1.userrelation;

import com.eastflag.nnc.exception.BaseException;
import com.eastflag.nnc.user1.request.UpdateUserRelationRequest;
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
                .orElseThrow(() -> new BaseException(CARETAKER_ID_NOT_FOUND));

        userRelation.setCaregiverId(request.getCaregiverId());
        userRelation.setRelation(request.getRelation());

        userRelationRepository.save(userRelation);

        return userRelation;
    }

    /**
     * 유저 관계 Entity를 DataBase에서 삭제하는 힘수
     * 사용자 계정이 삭제될 때 호출된다.
     */
    public void deleteUserRelation(int userId) {
        // ※ userId가 Caregiver인 경우는 deleteUser()에서 관리한다.
        var userRelation = userRelationRepository
                .findByCaretakerId(userId)
                .orElseThrow(() -> new BaseException(CARETAKER_ID_NOT_FOUND));

        userRelationRepository.deleteById(userRelation.getUserRelationId());
    }

    public UserRelation getUserRelation(int userId) {
        var userRelation = userRelationRepository.findByCaretakerId(userId)
                .orElseGet(() -> userRelationRepository.findByCaregiverId(userId)
                .orElseThrow(() -> new BaseException(RELATION_USER_ID_NOT_FOUND)));
        return userRelation;
    }

    public int getAnotherUserId(int userId) {
        var userRelation = getUserRelation(userId);

        var caretakerId = userRelation.getCaretakerId();
        var caregiverId = userRelation.getCaregiverId();

        if(userId == caretakerId) return caregiverId;
        else return caretakerId;
    }

    // 유저 관계가 없는 것을 알려주는 함수
    public UserRelation findUserRelation(int userId) {
        var userRelation = userRelationRepository.findByCaretakerId(userId).orElseGet(() -> userRelationRepository
                .findByCaregiverId(userId)
                .orElse(null));
        return userRelation;
    }

    // 상대 유저가 없는 것을 알려주는 함수
    public boolean isAnotherUserId(int userId) {
        var userRelation = findUserRelation(userId);

        if(userRelation != null) return true;
        else return false;
    }
}
