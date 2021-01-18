package br.com.alelo.frota.exceptions;

import lombok.Data;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;
    private Map<String, List<RepositoryConstraintViolationExceptionMessage.ValidationError>> errors = new HashMap<>();
}
