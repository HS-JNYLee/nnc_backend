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
                //.passwordSalt() // 모름
                //.hashAlgorithmId() // 모름
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
        var beforeUserAccount = userAccountRepository
                .findById(userAccountId)
                .orElseThrow(() -> new RuntimeException(userAccountId + "를 찾을 수 없음."));

        var userAccount = UserAccount.builder()
                .userAccountId(userAccountId)
                .email(request.getEmail() != null ? request.getEmail() : beforeUserAccount.getEmail())
                .password(request.getPassword() != null ? request.getPassword() : beforeUserAccount.getPassword())
                .passwordSalt(request.getPasswordSalt() != null ? request.getPasswordSalt() : beforeUserAccount.getPasswordSalt())
                .hashAlgorithmId(request.getHashAlgorithmId() != null ? request.getHashAlgorithmId() : beforeUserAccount.getHashAlgorithmId())
                .updatedAt(request.getUpdatedAt() != null ? request.getUpdatedAt() : beforeUserAccount.getUpdatedAt())
                .address(request.getAddress() != null ? request.getAddress() : beforeUserAccount.getAddress())
                .detailAddress(request.getDetailAddress() != null ? request.getDetailAddress() : beforeUserAccount.getDetailAddress())
                .build();
        userAccountRepository.save(userAccount);

        return userAccount;
    }

}
