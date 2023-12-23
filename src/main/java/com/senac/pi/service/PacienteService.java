
package com.senac.pi.service;

import com.senac.pi.model.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacRepository;
}
