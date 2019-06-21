package br.com.bapadua.aws.domain.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateDTO {

	@NotBlank(message = "Informe o subject")
	private String subject;
	private String description;
	
	@NotNull(message = "Informe o estado da requisição")
	private RequestState state;

	@NotNull(message = "Informe o usuário")
	private User owner;

	private List<RequestStage> stages;

	public Request toRequest() {
		return new Request(null, subject, description, null, state, owner, stages);
	}

}
