package com.eastflag.nnc.testkmj.UserAccount;

import com.eastflag.nnc.testkmj.User.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public final createUserAccount(int userId, CreateUserRequest request){

    }
}
