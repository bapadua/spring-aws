package br.com.bapadua.aws.domain.dto;

import javax.validation.constraints.NotNull;

import br.com.bapadua.aws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRoleUpdateDTO {
	
	@NotNull(message = "Informe uma role")
	private Role role;
}
