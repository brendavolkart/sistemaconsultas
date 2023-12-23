
package com.senac.pi.controller;

import com.senac.pi.model.Consulta;
import com.senac.pi.model.ConsultaRepository;
import com.senac.pi.model.Medico;
import com.senac.pi.model.MedicoRepository;
import com.senac.pi.model.Paciente;
import com.senac.pi.model.PacienteRepository;
import com.senac.pi.service.ConsultaService;
import com.senac.pi.service.MedicoService;
import com.senac.pi.service.PacienteService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerFront {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;
     
    @GetMapping({"/consultas", "/"})
    public String viewHomePage(Model model) {
        List<Consulta> consultas = consultaRepository.findAll();
        model.addAttribute("consultas", consultas);
        return "pagina-consultas";
    }
    
    @GetMapping("/consulta/excluir/{id}")
    @Transactional
    public String excluirConsulta(@PathVariable Long id) {
        consultaRepository.deleteById(id);

        return "redirect:/consultas";
    }
    
    @GetMapping("/cadastroPac")
    public String viewPagCadasPac(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pagina-cadastro-paciente";
    }
    
    @GetMapping("/cadastroMed")
    public String viewPagCadasMed(Model model) {
        model.addAttribute("medico", new Medico());
        return "pagina-cadastro-medico";
    }

    @PostMapping("/marcarConsultas")
    public String agendarConsultas(Consulta consulta) {        
        consultaRepository.save(consulta);
        return "redirect:/consultas";
    }
    
    @GetMapping("/marcarConsultas")
    public String viewPagConsultas(Model model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<Medico> medicos = medicoRepository.findAll();        
        LocalDate amanha = LocalDate.now().plusDays(1);
        LocalDate umaSemanaDepois = LocalDate.now().plusDays(7);
     
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("dataMinima", amanha);
        model.addAttribute("dataMaxima", umaSemanaDepois);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);
        
        return "pagina-marcar-consulta";
    }
    
    @PostMapping("/cadastroPac")
    public String salvarPaciente(Model model, Paciente paciente) {
        pacienteRepository.save(paciente);
        model.addAttribute("mensagem", "Paciente cadastrado com sucesso!");
        model.addAttribute("paciente", new Paciente());
        return "/pagina-cadastro-paciente"; // Página de sucesso após o cadastro
    }
    
    @PostMapping("/cadastroMed")
    public String salvarMedico(Model model, Medico medico) {
        medicoRepository.save(medico);
        model.addAttribute("mensagem", "Médico cadastrado com sucesso!");
        model.addAttribute("medico", new Medico());
        return "/pagina-cadastro-medico"; // Página de sucesso após o cadastro
    }
}
