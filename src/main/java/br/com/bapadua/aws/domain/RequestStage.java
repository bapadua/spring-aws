package br.com.bapadua.aws.domain;

import java.util.Date;

import br.com.bapadua.aws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestStage {
	private Long id;
	private String description;
	private Date  requestDate;
	private RequestState state;
	private Request request;
	private User user;
}
