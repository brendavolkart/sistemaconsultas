
package com.senac.pi.service;

import com.senac.pi.model.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consulRepository;
}
