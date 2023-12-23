
package com.senac.pi.service;

import com.senac.pi.model.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medRepository;
}
