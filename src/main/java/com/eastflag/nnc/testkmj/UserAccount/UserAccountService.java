package com.eastflag.nnc.testkmj.UserAccount;

import com.eastflag.nnc.testkmj.User.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public void createUserAccount(int userId, CreateUserRequest request){
        var userAccount = UserAccount.builder()
                .userId(userId)
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
    }
}
