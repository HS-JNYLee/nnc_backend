package com.eastflag.nnc.user1.user;

import com.eastflag.nnc.user.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User1의 역할 및 권한을 명시하는 enum
 * ADMIN (관리자): 시스템 관리 계정
 * CAREGIVER (보호자): 보호자 계정
 * CARETAKER (사용자): 피보호자 계정
 */
@RequiredArgsConstructor
public enum Role1 {
    ADMIN(Collections.emptySet()),
    CAREGIVER(Collections.emptySet()),
    CARETAKER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
