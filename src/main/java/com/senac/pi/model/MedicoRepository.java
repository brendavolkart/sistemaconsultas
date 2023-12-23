package com.senac.pi.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Medico findByNome(String nome);

    List<Medico> findByNomeOrCrm(String nome, String crm);
}
