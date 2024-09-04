package com.eastflag.nnc.navigation;

import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.user1.userrelation.UserRelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.exception.errorcode.NavigationException.*;

/**
 * 경로 안내 관리 Service 클래스
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class NavigationService {
    private final NavigationRepository navigationRepository;
    private final UserRelationService userRelationService;

    /**
     * Navigation Entity를 DataBase에 생성하는 함수
     * ※ 이미 경로가 존재한다면, 해당 navigation 레코드를 수정하고, 경로가 없다면 새로 생성한다.
     *
     * @param userId 생성할 이용자의 정보
     * @param transportRoute 경로에 대한 정보가 표시되는 객체 Json
     *
     * @return 생성된 navigation Entity
     */
    public String create(int userId, String transportRoute) {
        var relation = userRelationService.getUserRelation(userId);
        var caretakerId= relation.getCaretakerId();
        var caregiverId = relation.getCaregiverId();

        var navigation = navigationRepository
                .findByCaretakerId(caretakerId)
                .orElse(
                        Navigation.builder()
                                .caretakerId(caretakerId)
                                .caregiverId(caregiverId)
                                .build()
                );
        navigation.setTransportRoute(transportRoute);
        navigationRepository.save(navigation);
        return transportRoute;
    }

    /**
     * 특정 transportRoute 데이터를 반환하는 함수
     *
     * @param userId 반환 받을 transport 이용자 Id
     * @return userId에 맞는 transport
     */
    public String getTransportRoute(int userId) {
        var navigation = navigationRepository.findByCaregiverId(userId)
                .orElseGet(() -> navigationRepository.findByCaretakerId(userId)
                .orElseThrow(() -> new ControlledException(RELATION_ID_NOT_FOUND)));

        return navigation.getTransportRoute();
    }

    /**
     * 특정 Entity 데이터를 변경하는 함수
     *
     * @param caretakerId 경로를 재탐색한 사용자 ID
     * @param route 경로 좌표 리스트 객체 Json
     */
    public void updateRoute(int caretakerId, String route) {
        var navigation = navigationRepository
                .findByCaretakerId(caretakerId)
                .orElseThrow(() -> new ControlledException(CARETAKER_ID_NOT_FOUND));
        navigation.setRoute(route);
        navigationRepository.save(navigation);
    }

    /**
     * 특정 route 데이터를 반환하는 함수
     * @param caregiverId 재탐색 된 경로를 조회하는 보호자 ID
     * @return 재탐색된 경로 좌표 리스트 객체 Json
     */
    public String getRoute(int caregiverId) {
        var navigation = navigationRepository
                .findByCaregiverId(caregiverId)
                .orElseThrow(() -> new ControlledException(CAREGIVER_ID_NOT_FOUND));
        return navigation.getRoute();
    }

    /**
     * Navigation 삭제
     *
     * @param userId navigation 레코드에 포함된 이용자 ID
     */
    public void deleteNavigation(int userId) {
        var navigation = navigationRepository.findByCaretakerId(userId).orElse(
                navigationRepository.findByCaregiverId(userId).orElseThrow(
                        () -> new ControlledException(RELATION_ID_NOT_FOUND)
                )
        );
        navigationRepository.deleteById(navigation.getNavigationId());
    }
}
