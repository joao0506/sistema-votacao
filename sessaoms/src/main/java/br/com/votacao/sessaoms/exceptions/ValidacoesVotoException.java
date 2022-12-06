package br.com.votacao.sessaoms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacoesVotoException extends RuntimeException{

    public ValidacoesVotoException(String msg){
        super(msg);
    }

}
