package com.demo.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usuarios.entity.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario,Long>{

}
