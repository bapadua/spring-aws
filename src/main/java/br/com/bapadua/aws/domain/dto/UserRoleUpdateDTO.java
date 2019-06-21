package br.com.bapadua.aws.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.bapadua.aws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRoleUpdateDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Informe uma role")
	private Role role;
}
