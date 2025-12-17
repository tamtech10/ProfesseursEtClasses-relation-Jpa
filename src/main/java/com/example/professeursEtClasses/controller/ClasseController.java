package com.example.professeursEtClasses.controller;


import com.example.professeursEtClasses.model.Classe;
import com.example.professeursEtClasses.model.Professeur;
import com.example.professeursEtClasses.service.ClasseService;
import com.example.professeursEtClasses.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @Autowired
    private ProfesseurService professeurService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }


    //Liste tous les professeurs
    @GetMapping("/")
    public String listClasses(Model model) {
        model.addAttribute("classes", classeService.getAllClasses());
        return "classes/index";
    }

    // Formulaire de création de la classe
    @GetMapping("/nouveau")
    public String showFormulaire(Model model) {
        model.addAttribute("classe", new Classe());
        model.addAttribute("professeurs", professeurService.getAllProfesseurs());
        return "classes/formulaire";
    }

    //Créer une nouvelle classe
    @PostMapping("/nouveau")
    public String createClasse(@ModelAttribute Classe classe) {
        classeService.createClasse(classe);
        return "redirect:/classes/";
    }

    //Voir une classe
    @GetMapping("/{id}")
    public String showClasse(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.getClasseById(id));
        return "classes/detail";
    }

    //Formulaire de modification
    @GetMapping("/{id}/modifier")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.getClasseById(id));
        return "classes/formulaire";
    }

    // Modifier un professeur
    @PostMapping("/{id}/modifier")
    public String updateClasse(@ModelAttribute Classe classe) {
        classeService.updateClasse(classe);
        return "redirect:/classes/";
    }

    // Supprimer un professeur
    @GetMapping("/{id}/supprimer")
    public String deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return "redirect:/classes/";
    }

}
