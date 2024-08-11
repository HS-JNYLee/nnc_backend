package com.eastflag.nnc.testkmj.useraccount;

import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

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

    public void deleteUserAccount(int userAccountId) {
        userAccountRepository.deleteById(userAccountId);
    }

    public UserAccount updateUserAccount(int userAccountId, UpdateUserRequest request) {
        var userAccount = userAccountRepository
                .findById(userAccountId)
                .orElseThrow(() -> new RuntimeException(userAccountId + "를 찾을 수 없음."));

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

    public UserAccount getUserAccount(int userAccountId) {
        var userAccount = userAccountRepository
                .findById(userAccountId)
                .orElseThrow(() -> new RuntimeException(userAccountId + "를 찾을 수 없음."));
        return userAccount;
    }
}