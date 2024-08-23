package com.eastflag.nnc.user1.usersetting;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserSetting Table 접근 인터페이스
 */
public interface UserSettingRepository extends JpaRepository<UserSetting, Integer> {

}
