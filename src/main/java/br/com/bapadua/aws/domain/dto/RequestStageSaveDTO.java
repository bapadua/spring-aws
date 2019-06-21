package br.com.bapadua.aws.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStageSaveDTO {
	
	private String description;
	
	@NotNull(message = "Informe o estado da requisição")
	private RequestState state;
	
	@NotNull(message = "Informa a requisicao")
	private Request request;
	
	@NotNull(message = "Informe o usuario")
	private User owner;
		
	public RequestStage toStage() {
		return new RequestStage(null, description, new Date(), state, request, owner);
	}
	
}
