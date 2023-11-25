package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.serializers.premio.DeletePremioRequest;
import com.learnsyc.appweb.serializers.premio.EditPremioRequest;
import com.learnsyc.appweb.serializers.premio.PremioSerializer;
import com.learnsyc.appweb.serializers.premio.SavePremioRequest;
import com.learnsyc.appweb.services.PremioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("premio")
@CrossOrigin(origins = "http://localhost:4200")
public class PremioController {

    @Autowired
    PremioService premioService;

    @GetMapping("/")
    public List<PremioSerializer> listarPremios() {
        return premioService.listarPremios().stream()
                .map((it) -> premioService.retornarPremio(it.getIdPremio()))
                .toList();
    }

    @PostMapping("/")
    public Premio crearPremio(@Valid @RequestBody SavePremioRequest request) {
        return premioService.guardarPremio(request);
    }

    @PutMapping("/")
    public Premio editarPremio(@Valid @RequestBody EditPremioRequest request) {
        return premioService.editarPremio(request);
    }

    @DeleteMapping("/")
    public Premio eliminarPremio(@Valid @RequestBody DeletePremioRequest request) {
        return premioService.eliminarPremio(request);
    }
}
