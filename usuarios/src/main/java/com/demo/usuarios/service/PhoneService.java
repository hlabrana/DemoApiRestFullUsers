package com.demo.usuarios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.usuarios.dto.PhoneDTO;
import com.demo.usuarios.entity.Phone;

@Service
public class PhoneService {
	
	public List<PhoneDTO> phonesToDTO(List<Phone> phones) {
		return phones
				.stream()
				.map(this::converterDTO)
				.collect(Collectors.toList());
	}
	
	public List<Phone> mergePhones(List<Phone> oldPhones, List<Phone> newPhones){
		for(Phone newPhone : newPhones) {
			boolean duplicate = false;
			for(Phone oldPhone : oldPhones) {
				if(oldPhone.getNumber().equals(newPhone.getNumber())) {
					duplicate = true;
				}
			}
			
			if(!duplicate) {
				oldPhones.add(newPhone);
			}
		}
		return oldPhones;
	}
	
	private PhoneDTO converterDTO(Phone phone) {
		PhoneDTO phoneDTO = new PhoneDTO();
		phoneDTO.setNumber(phone.getNumber());
		phoneDTO.setCitycode(phone.getCitycode());
		phoneDTO.setCountrycode(phone.getCountrycode());
		return phoneDTO;
	}

}
