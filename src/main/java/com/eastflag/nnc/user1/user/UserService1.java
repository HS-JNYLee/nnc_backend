package com.eastflag.nnc.user1.user;

import com.eastflag.nnc.fcm.FcmService;
import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.user1.userrelation.UserRelationService;
import com.eastflag.nnc.user1.request.CreateUserRequest;
import com.eastflag.nnc.user1.request.UpdateUserRequest;
import com.eastflag.nnc.user1.useraccount.UserAccount;
import com.eastflag.nnc.user1.useraccount.UserAccountService;
import com.eastflag.nnc.user1.usersetting.UserSetting;
import com.eastflag.nnc.user1.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.exception.errorcode.User1ErrorCode.*;

/**
 * 유저 관리 Service 클래스
 *
 * UserAccountService, UserSettingService와 종속 관계이다.
 * ※ 두 Service에서는 접근하지 말 것.
 */
@Service
@RequiredArgsConstructor
public class UserService1 {
    private final UserRepository1 userRepository;
    private final UserAccountService userAccountService;
    private final UserSettingService userSettingService;
    private final UserRelationService userRelationService;
    private final FcmService fcmService;

    /**
     * 유저 Entity를 DataBase에서 생성하는 함수
     *
     * @param request UserController1.createUser API에서 가져온 유저 생성 정보
     * @return 생성된 유저 Entity
     */
    //TODO: 모든 작업 완료되지 않았으면 반환하지 않기
    public User1 createUser(CreateUserRequest request) {
        // request 정보들 각 userAccount, userSetting으로 분기
        // 생성에 userAccountId와 userSettingId가 필요하기 때문
        UserAccount userAccount = userAccountService.createUserAccount(request);
        UserSetting userSetting = userSettingService.createUserSetting();

        // Relation 관계는 CareTaker 생성 시 생성
        Role1 role;
        if(request.getCaregiverId() == 0) role = Role1.CAREGIVER;
        else role = Role1.CARETAKER;

        var user = User1.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .role1(role)
                .userAccount(userAccount)
                .userSetting(userSetting)
                .build();

        userRepository.save(user);

        fcmService.createFcm(user.getUserId(),request.getFcmToken());

        if(role == Role1.CARETAKER)
            userRelationService.createUserRelation(user.getUserId(),request.getCaregiverId(), request.getCaregiverRelation());

        return user;
    }

    /**
     * 유저 Entity를 DataBase에서 삭제하는 함수
     *
     * @param userId 삭제할 Entity ID
     */
    public void deleteUser(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ControlledException(USER_ID_NOT_FOUND));

        // Caretaker인 경우 user_relation도 삭제해야 한다.
        // Caregiver의 경우 Caretaker가 있다면 삭제가 불가능하게 해야한다.
        if(user.getRole1() == Role1.CARETAKER) userRelationService.deleteUserRelation(userId);
        else if(userRelationService.isAnotherUserId(userId)) throw new ControlledException(CAREGIVER_IS_NOT_DELETE);

        // 종속관계 제거를 위한 userAccount, userSetting 분기
        var userSettingId = getUserSettingId(userId);
        var userAccountId = getUserAccountId(userId);
        userRepository.deleteById(user.getUserId());
        userSettingService.deleteUserSetting(userSettingId);

        userAccountService.deleteUserAccount(userAccountId);
    }

    /**
     * 특정 Entity 데이터를 변경하는 함수
     *
     * @param request UserController1.updateUser에서 가져온 정보
     * @return 변경된 User1 Entity
     */
    public User1 updateUser(UpdateUserRequest request) {
        var user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new ControlledException(USER_ID_NOT_FOUND));

        // null이 아닌 값만 setter로 수정한다.
        if(request.getName() != null) user.setName(request.getName());
        if(request.getTelNum() != null) user.setTelNum(request.getTelNum());

        // user_account Table에 관한 request는 분기하여 update한다.
        var userAccountId = getUserAccountId(request.getUserId());
        UserAccount userAccount = userAccountService.updateUserAccount(userAccountId, request);
        user.setUserAccount(userAccount);

        userRepository.save(user);

        return user;
    }

    /**
     * 특정 Entity 데이터를 반환하는 함수
     *
     * @param userId 반환받을 User1의 user_id
     * @return user_id에 맞는 User1 Entity
     */
    public User1 getUser(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ControlledException(USER_ID_NOT_FOUND));
        return user;
    }

    public User1 getUser(UserAccount userAccount) {
        var user = userRepository
                .findByUserAccount(userAccount)
                .orElseThrow(() -> new ControlledException(USER_ACCOUNT_NOT_FOUND));
        return user;
    }

    public User1 getUser(String telNum) {
        var user = userRepository.findByTelNum(telNum)
                .orElseThrow(() -> new ControlledException(NO_TELNUM));
        return user;
    }

    /**
     * user1.userId를 통해 user_account_id를 조회하는 함수
     *
     * @param userId 조회할 user_id
     * @return user_id에 대응하는 user_account_id
     */
    public int getUserAccountId(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ControlledException(USER_ID_NOT_FOUND));
        return user.getUserAccount().getUserAccountId();
    }

    /**
     * user1.userId를 통해 user_setting_id를 조회하는 함수
     *
     * @param userId 조회할 user_id
     * @return user_id에 대응하는 user_setting_id
     */
    public int getUserSettingId(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ControlledException(USER_ID_NOT_FOUND));
        return user.getUserSetting().getUserSettingId();
    }

    /**
     * login 기능 구현 함수
     *
     * @param email login할 계정 email
     * @param password login할 계정 password
     * @return login된 User Entity ※ userAccountService.getLoginUserAccount에서 null 반환 시 null
     */
    public User1 login(String email, String password) {
        // 조회를 하지 못할 시 null을 반환한다.
        var userAccount = userAccountService.getLoginUserAccount(email, password);
        if(userAccount == null) return null;

        var user = userRepository
                .findByUserAccount(userAccount)
                .orElseThrow(() -> new ControlledException(USER_ACCOUNT_NOT_FOUND));
        return user;
    }
}