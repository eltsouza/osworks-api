package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.OrdemServicoRepresatationModel;
import com.algaworks.osworks.api.model.OrdemServicoRepresentationInputModel;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemSericoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemSericoService gestaoOrdemSericoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
  //public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
	public OrdemServicoRepresatationModel criar(@Valid @RequestBody OrdemServicoRepresentationInputModel ordemServicoRepInput) {
		
	    OrdemServico ordemServico = toEntity(ordemServicoRepInput);		
	    
		//return gestaoOrdemSericoService.criar(ordemServico);
	    
	    return toModel(gestaoOrdemSericoService.criar(ordemServico));		
	}
	
	@GetMapping
	//public List<OrdemServico> listar(){
	public List<OrdemServicoRepresatationModel> listar(){	
	
	  //return ordemServicoRepository.findAll();
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	//public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId){
	public ResponseEntity<OrdemServicoRepresatationModel> buscar(@PathVariable Long ordemServicoId){
	
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if (ordemServico.isPresent()) {
			
			OrdemServicoRepresatationModel osRepresentationModel = toModel(ordemServico.get());
			return ResponseEntity.ok(osRepresentationModel);
			//return ResponseEntity.ok(ordemServico.get());
		}
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
        gestaoOrdemSericoService.finalizar(ordemServicoId);
	}
	
	
	private OrdemServicoRepresatationModel toModel(OrdemServico ordemServico) {	
		return modelMapper.map(ordemServico, OrdemServicoRepresatationModel.class);
	}
    
	//Este código retorna um lista de Ordens de Servico do Tipo OrdemServicoRepresatationModel
	private List<OrdemServicoRepresatationModel> toCollectionModel(List<OrdemServico>ordensServico){
		return ordensServico.stream()
				.map(OrdemServico -> toModel(OrdemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoRepresentationInputModel ordemServicoRepInput) {
		return modelMapper.map(ordemServicoRepInput, OrdemServico.class);
	}

}
