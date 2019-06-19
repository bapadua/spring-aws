package br.com.bapadua.aws.domain.dto;

import br.com.bapadua.aws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateDTO {
	private Role role;
}
