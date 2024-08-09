package com.eastflag.nnc.testkmj.useraccount;

import com.eastflag.nnc.testkmj.user.CreateUserRequest;
import com.eastflag.nnc.testkmj.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public void createUserAccount(User user, CreateUserRequest request){
        var userAccount = UserAccount.builder()
                .user(user)
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
