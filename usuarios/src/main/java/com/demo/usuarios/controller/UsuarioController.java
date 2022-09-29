package com.demo.usuarios.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usuarios.dto.UsuarioDTO;
import com.demo.usuarios.entity.Usuario;
import com.demo.usuarios.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/user")
	public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
		try {
			List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
			
			if(usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{ID}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long ID) {
		try {
			UsuarioDTO usuario = usuarioService.getUsuario(ID);
			
			if(usuario == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody Usuario user) {
		try {
			return new ResponseEntity<>(usuarioService.addUsuario(user),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id,@RequestBody Usuario user) {
		try {
			UsuarioDTO _usuario = usuarioService.updateUsuario(id, user);
			if(_usuario != null) {
				return new ResponseEntity<>(_usuario,HttpStatus.OK);
			}
			else {				
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) {
		try {
			usuarioService.deleteUsuario(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
