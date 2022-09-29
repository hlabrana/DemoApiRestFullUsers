package com.demo.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.usuarios.entity.Phone;

@Repository
public interface IPhoneDAO extends JpaRepository<Phone,Long>{

}
