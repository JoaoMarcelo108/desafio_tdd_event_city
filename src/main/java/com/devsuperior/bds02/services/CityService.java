package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAllByOrderByNameAsc(){
        List<City> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(CityDTO::new).toList();
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity =  new City();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CityDTO(entity);
    }
    
    private void copyDtoToEntity(CityDTO dto, City entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
    }


}
