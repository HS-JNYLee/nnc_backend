package com.eastflag.nnc.auth;

import com.eastflag.nnc.user.user.User1;
import com.eastflag.nnc.user.user.UserRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository1 userRepository;

    @Override
    public UserDetails loadUserByUsername(String telNum) throws UsernameNotFoundException {
        Optional<User1> userEntity = userRepository.findByTelNum(telNum);

        return userEntity.map(CustomUserDetails::new).orElseGet(() -> new CustomUserDetails(null));
    }
}
