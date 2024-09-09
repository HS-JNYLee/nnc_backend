package com.eastflag.nnc.auth;

import com.eastflag.nnc.user.user.Role1;
import com.eastflag.nnc.user.user.User1;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User1 user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //user객체에 유저 역활 뺴오기
        Role1 role = user.getRole1();

        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getTelNum();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
