package com.senac.pi.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer>  {

    public void deleteById(Long id);

    public Consulta findById(Long id);

}
