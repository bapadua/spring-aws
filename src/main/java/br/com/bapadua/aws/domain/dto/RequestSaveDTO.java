package br.com.bapadua.aws.domain.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSaveDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Informe o subject")
	private String subject;
	private String description;
	
	@NotNull(message = "Informe o usuario")
	private User owner;
	
	private List<RequestStage> stages;
	
	public Request toRequest() {
		return new Request(null, subject, description, null, null, owner, stages);
	}
	
}
