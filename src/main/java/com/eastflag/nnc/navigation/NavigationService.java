package com.eastflag.nnc.navigation;

import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.user1.userrelation.UserRelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.exception.errorcode.NavigationException.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class NavigationService {
    private final NavigationRepository navigationRepository;
    private final UserRelationService userRelationService;

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
                                .transportRoute(transportRoute)
                                .build()
                );
        navigationRepository.save(navigation);
        return transportRoute;
    }

    public String getTransportRoute(int caregiverId) {
        var navigation = navigationRepository.findByCaregiverId(caregiverId)
                .orElseThrow(() -> new ControlledException(CAREGIVER_ID_NOT_FOUND));
        return navigation.getTransportRoute();
    }

    public void updateRoute(int caretakerId, String route) {
        var navigation = navigationRepository
                .findByCaretakerId(caretakerId)
                .orElseThrow(() -> new ControlledException(CARETAKER_ID_NOT_FOUND));
        navigation.setRoute(route);
        navigationRepository.save(navigation);
    }

    public String getRoute(int caregiverId) {
        var navigation = navigationRepository
                .findByCaregiverId(caregiverId)
                .orElseThrow(() -> new ControlledException(CAREGIVER_ID_NOT_FOUND));
        return navigation.getRoute();
    }

    public void deleteNavigation(int userId) {
        var navigation = navigationRepository.findByCaretakerId(userId).orElse(
                navigationRepository.findByCaregiverId(userId).orElseThrow(
                        () -> new ControlledException(RELATION_ID_NOT_FOUND)
                )
        );
        navigationRepository.deleteById(navigation.getNavigationId());
    }
}
