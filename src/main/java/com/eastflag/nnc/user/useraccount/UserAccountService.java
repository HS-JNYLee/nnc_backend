package com.eastflag.nnc.user.useraccount;

import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.user.request.CreateUserRequest;
import com.eastflag.nnc.user.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.exception.errorcode.UserAccountErrorCode.*;

/**
 * 유저 계정 관리 Service 클래스
 *
 */
@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    /**
     * 유저 계정 Entity를 DataBase에 생성하는 함수
     *
     * @param request UserController1.createUser API에서 가져온 유저 생성 정보
     * @return 생성된 유저 계정 Entity
     */
    public UserAccount createUserAccount(CreateUserRequest request){
        var userAccount = UserAccount.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .passwordSalt("temp") // 모름
                .hashAlgorithmId("temp") // 모름
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();
        userAccountRepository.save(userAccount);

        return userAccount;
    }

    /**
     * 유저 계정 Entity를 DataBase에 삭제하는 함수
     *
     * @param userAccountId 삭제할 Entity ID
     */
    public void deleteUserAccount(int userAccountId) {
        userAccountRepository.deleteById(userAccountId);
    }

    /**
     * 특정 Entity 데이터를 변경하는 함수
     *
     * @param userAccountId 변경할 Entity ID
     * @param request UserController1.updateUser에서 가져온 정보
     * @return 변경된 UserAccount Entity
     */
    public UserAccount updateUserAccount(int userAccountId, UpdateUserRequest request) {
        var userAccount = userAccountRepository
                .findById(userAccountId)
                .orElseThrow(() -> new ControlledException(USER_ACCOUNT_ID_NOT_FOUND));

        // null이 아닌 값만 setter로 수정한다.
        if(request.getEmail() != null) userAccount.setEmail(request.getEmail());
        if(request.getPassword() != null) userAccount.setPassword(request.getPassword());
        if(request.getPasswordSalt() != null) userAccount.setPasswordSalt(request.getPasswordSalt());
        if(request.getHashAlgorithmId() != null) userAccount.setHashAlgorithmId(request.getHashAlgorithmId());
        if(request.getUpdatedAt() != null) userAccount.setUpdatedAt(request.getUpdatedAt());
        if(request.getAddress() != null) userAccount.setAddress(request.getAddress());
        if(request.getDetailAddress() != null) userAccount.setDetailAddress(request.getDetailAddress());

        userAccountRepository.save(userAccount);

        return userAccount;
    }

    /**
     * 특정 Entity 데이터를 반환하는 함수
     *
     * @param userAccountId 반환받을 UserAccount의 user_account_id
     * @return user_account_id에 맞는 UserAccount Entity
     */
    public UserAccount getUserAccount(int userAccountId) {
        var userAccount = userAccountRepository
                .findById(userAccountId)
                .orElseThrow(() -> new ControlledException(USER_ACCOUNT_ID_NOT_FOUND));
        return userAccount;
    }

    /**
     * 특정 Entity 데이터를 반환하는 함수
     *
     * @param userAccountEmail 반환받을 UserAccount의 user_account_email
     * @return user_account_id에 맞는 UserAccount Entity
     */
    public UserAccount getUserAccount(String userAccountEmail) {
        var userAccount = userAccountRepository
                .findByEmail(userAccountEmail)
                .orElseThrow(() -> new ControlledException(USER_ACCOUNT_EMAIL_NOT_FOUND));
        return userAccount;
    }

    /**
     * 로그인 정보를 통해 user_account를 조회하는 함수
     *
     * @param email login할 계정 email
     * @param password login할 계정 password
     * @return login된 UserAccount Entity ※ email과 password가 일치하지 않을 시 null을 반환 
     */
    public UserAccount getLoginUserAccount(String email, String password) {
        var userAccount = userAccountRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);
        return userAccount;
    }
}