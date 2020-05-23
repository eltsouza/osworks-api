package com.algaworks.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.osworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	   @ExceptionHandler(EntidadeNaoEncontradaException.class)
	   public ResponseEntity<Object> handleEntidadeNaoEncontradaException(NegocioException ex, WebRequest request) {
		   
		   var status = HttpStatus.NOT_FOUND;
		   var problema = new Problema();
		   problema.setStatus(status.value());
		   problema.setTitulo(ex.getMessage());
		   problema.setDataHora(OffsetDateTime.now());
		   
	       return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	   }
			
	
   @ExceptionHandler(NegocioException.class)
   public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
	   
	   var status = HttpStatus.BAD_REQUEST;
	   var problema = new Problema();
	   problema.setStatus(status.value());
	   problema.setTitulo(ex.getMessage());
	   problema.setDataHora(OffsetDateTime.now());
	   
       return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
   }
	
   @Autowired	
   private MessageSource messageSource;	
	
   @Override	
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		   HttpHeaders headers, HttpStatus status, WebRequest request) {
	     
	      var campos = new ArrayList<Problema.Campo>();
	      
	      for (ObjectError error : ex.getBindingResult().getAllErrors()) {
	    	  String campo = ((FieldError)error).getField();//cache para pegar o nome do campo com erro
	    	  String mensagem = messageSource.getMessage(error,LocaleContextHolder.getLocale());//LocaleContextHolder, retornar a lingua da localização.
	    	  campos.add(new Problema.Campo(campo,mensagem));
	      }
	   
	      var problema = new Problema();	      
	      problema.setStatus(status.value());
	      problema.setTitulo("Um ou mais campos estão inválidos." 
                +  "Faça o preenchimento correto e tente novamente");
	      problema.setDataHora(OffsetDateTime.now());
	      
	      problema.setCampos(campos);
	      
          return handleExceptionInternal(ex, problema, headers, status, request);
    		
   }

}
