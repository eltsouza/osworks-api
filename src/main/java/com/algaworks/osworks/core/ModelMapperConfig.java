package com.algaworks.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Essa notação é um componente spring com o objetivo de configuração de novos beans.
@Configuration
public class ModelMapperConfig {
	
	//Essa notacao indica a inicialização de um Bean do tipo Mapper.
	@Bean
	public ModelMapper modeMapper() {
		return new ModelMapper();		
	}

}
