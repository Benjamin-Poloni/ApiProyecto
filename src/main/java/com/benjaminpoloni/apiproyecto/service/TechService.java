package com.benjaminpoloni.apiproyecto.service;

import com.benjaminpoloni.apiproyecto.models.Tech;
import com.benjaminpoloni.apiproyecto.repository.TechRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TechService {


    private final TechRepository techRepository;



    public List<Tech> findAll() {
        return techRepository.findAll();
    }

}
