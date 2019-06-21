package br.com.bapadua.aws.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
	
	@Email(message = "Informe um e-mail válido")
	private String email;
	
	@NotBlank(message = "Informe uma senha")
	@Length(min = 4, max = 8, message = "Deve conter entre 6 e 8 caracteres")
	private String password;
}
