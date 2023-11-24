package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.repositories.PremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {
    @Autowired
    PremioRepository premioRepository;

    // Método para listar todos los premios
    public List<Premio> listarPremios() {
        return premioRepository.findAll();
    }

    // Método para guardar un nuevo premio
    public Premio guardarPremio(Premio premio) {
        return premioRepository.save(premio);
    }

    // Método para editar un premio existente
    public Premio editarPremio(Premio premio) {
        return premioRepository.save(premio);
    }

    // Método para eliminar un premio
    public void eliminarPremio(Long idPremio) {
        premioRepository.deleteById(idPremio);
    }

    // Método para retornar un premio específico
    public Premio retornarPremio(Long idPremio) {
        return premioRepository.findById(idPremio).orElse(null);
    }

    // Método para comprar un premio (puedes ajustar según tu lógica específica)
    public void comprarPremio(Long idPremio) {
        // Implementa la lógica de compra aquí
    }
}