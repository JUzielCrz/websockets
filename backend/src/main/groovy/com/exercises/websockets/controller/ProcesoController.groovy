package com.exercises.websockets.controller

import com.exercises.websockets.service.ProcesoLargoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/proceso")
class ProcesoController {

    private final ProcesoLargoService servicio

    ProcesoController(ProcesoLargoService servicio) {
        this.servicio = servicio
    }

    @PostMapping("/iniciar")
    ResponseEntity<String> iniciar() {
        servicio.iniciarProcesoLargo()
        return ResponseEntity.ok("Proceso iniciado")
    }
}
