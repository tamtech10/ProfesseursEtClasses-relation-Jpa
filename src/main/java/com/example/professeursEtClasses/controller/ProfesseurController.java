package com.example.professeursEtClasses.controller;

import com.example.professeursEtClasses.model.Professeur;
import com.example.professeursEtClasses.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professeurs")
public class ProfesseurController {

    private final ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    //Liste tous les professeurs
@GetMapping("/")
    public String listProfesseurs(Model model) {
        model.addAttribute("professeurs", professeurService.getAllProfesseurs());
        return "professeurs/index";
}

    // Formulaire de création
@GetMapping("/nouveau")
    public String showFormulaire(Model model) {
    model.addAttribute("professeur", new Professeur());
    return "professeurs/formulaire";
}

    //Créer un professeur
@PostMapping("/nouveau")
    public String createProfesseur(@ModelAttribute Professeur professeur) {
        professeurService.createProfesseur(professeur);
        return "redirect:/professeurs/";
}

    //Voir un professeur
@GetMapping("/{id}")
    public String showProfesseur(@PathVariable Long id, Model model) {
    Professeur professeur = professeurService.getProfesseurById(id);

        model.addAttribute("professeur", professeurService.getProfesseurById(id));
        model.addAttribute("nbClasses", professeurService.nbTotalClasses(id));
        model.addAttribute("nbEleves", professeurService.nbTotalEleves(id));
        model.addAttribute("moyenneEleves", professeurService.moyenneEleves(id));
        return "professeurs/details";
}

    //Formulaire de modification
@GetMapping("/{id}/modifier")
public String showEditForm(@PathVariable Long id, Model model) {
    model.addAttribute("professeur", professeurService.getProfesseurById(id));
    return "professeurs/formulaire";
}

    // Modifier un professeur
    @PostMapping("/{id}/modifier")
    public String updateProfesseur(@PathVariable Long id,
                                   @ModelAttribute Professeur professeur) {
        professeurService.updateProfesseur(id, professeur);
        return "redirect:/professeurs/";
    }

    // Supprimer un professeur
    @GetMapping("/{id}/supprimer")
    public String deleteProfesseur(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
        return "redirect:/professeurs/";
    }


}
