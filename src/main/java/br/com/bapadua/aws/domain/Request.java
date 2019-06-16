package br.com.bapadua.aws.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bapadua.aws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	private Long id;
	private String subject;
	private String description;
	private Date creationDate;
	private RequestState state;
	private User user;
	private List<RequestStage> stages = new ArrayList<RequestStage>();
}
