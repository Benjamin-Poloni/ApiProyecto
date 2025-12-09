package com.benjaminpoloni.apiproyecto.controller;


import com.benjaminpoloni.apiproyecto.models.Tech;
import com.benjaminpoloni.apiproyecto.service.TechService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tech")
public class TechController {
        private final TechService techService;
        public TechController(TechService techService) {
            this.techService = techService;
        }

    @GetMapping("/listarTechs")
    public ResponseEntity<?> listarTechs() {
        try {
            List<Tech> user =  techService.findAll();
            return  ResponseEntity.ok(user);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
