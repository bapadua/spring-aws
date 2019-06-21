package br.com.bapadua.aws.domain.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDTO {
	
	@NotBlank
	@Length(max = 50)
	private String name;
	
	@Email
	private String email;
	
	@NotBlank(message = "Informe uma senha")
	@Length(min = 4, max = 8, message = "Senha deve conter entre 6 e 8 caracteres")
	private String password;
	
	@NotNull(message = "Informe uma role")
	private Role role;
	private List<Request> requests;
	private List<RequestStage> stages;
	
	public User toUser() {
		return new User(null, name, email, password, role, requests, stages);
	}
}
