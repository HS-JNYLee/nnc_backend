package com.eastflag.nnc.auth;

import com.eastflag.nnc.user.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String nickname;
  @Email(message = "유효하지 않은 이메일입니다")
  private String email;
  private String password;
  private Role role = Role.USER;
}
