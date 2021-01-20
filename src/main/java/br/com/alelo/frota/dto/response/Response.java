package br.com.alelo.frota.dto.response;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

	private T data;
	private Object errors;
	private Pageable page;
	private Integer totalPages;
	private Long totalElements;
	
    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
        		.setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}