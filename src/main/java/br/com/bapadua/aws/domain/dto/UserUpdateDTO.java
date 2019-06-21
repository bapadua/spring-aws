package br.com.bapadua.aws.domain.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Informe um nome")
	@Length(max = 50, message = "O nome pode conter no máximo 50 caracteres")
	private String name;
	
	@Email(message = "Informe um e-mail válido")
	private String email;
	
	@NotBlank(message = "Informe uma senha")
	@Length(min = 4, max = 8, message = "Deve conter entre 6 e 8 caracteres")
	private String password;

	private List<Request> requests;
	private List<RequestStage> stages;
	
	public User toUser() {
		return new User(null, name, email, password, null, requests, stages);
	}
}
