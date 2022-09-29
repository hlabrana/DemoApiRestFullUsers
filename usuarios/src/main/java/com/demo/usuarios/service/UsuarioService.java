package com.demo.usuarios.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.usuarios.dto.UsuarioDTO;
import com.demo.usuarios.entity.Usuario;
import com.demo.usuarios.repository.IUsuarioDAO;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioDAO usuarioRepository;
	
	@Autowired
	private PhoneService phoneService;
	
	public List<UsuarioDTO> getAllUsuarios(){
		return usuarioRepository.findAll()
				.stream()
				.map(this::converterDTO)
				.collect(Collectors.toList());
	}
	
	public UsuarioDTO getUsuario(Long ID) {
		return this.converterDTO(usuarioRepository.findById(ID).get());
	}
	
	public UsuarioDTO addUsuario(Usuario user) {
		return this.converterDTO(usuarioRepository.save(user));
	}
	
	public UsuarioDTO updateUsuario(Long id,Usuario user) {
		Usuario _usuario = usuarioRepository.findById(id).get();
		if(user != null) {
			_usuario.setName(user.getName());
			_usuario.setEmail(user.getEmail());
			_usuario.setPassword(user.getPassword());
			_usuario.setPhones(phoneService.mergePhones(_usuario.getPhones(), user.getPhones()));
			_usuario.setUpdatedAt(new Date());
			usuarioRepository.save(_usuario);
			return this.converterDTO(_usuario);
		}
		else {
			return null;
		}
	}
	
	public void deleteUsuario(Long id) {
		Usuario user = usuarioRepository.findById(id).get();
		usuarioRepository.delete(user); 
	}
	
	private UsuarioDTO converterDTO(Usuario user) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setName(user.getName());
		usuarioDTO.setEmail(user.getEmail());
		usuarioDTO.setPassword(user.getPassword());
		usuarioDTO.setPhones(phoneService.phonesToDTO(user.getPhones()));
		usuarioDTO.setCreateAt(user.getCreateAt());
		usuarioDTO.setUpdatedAt(user.getUpdatedAt());
		return usuarioDTO;
	}

}
