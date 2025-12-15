package com.example.professeursEtClasses.service;

import com.example.professeursEtClasses.model.Professeur;
import com.example.professeursEtClasses.repository.ProfesseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {

    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    // 1. Lister tous les professeurs
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    //Obtenir un professeur par son ID
    public Professeur getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    //Créer un nouveau professeur
    public Professeur createProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    //Modifier un professeur
    public Professeur updateProfesseur(Long id, Professeur prof) {
        return professeurRepository.findById(prof.getId())
                .map(professeur -> {
                    professeur.setNom(prof.getNom());
                    professeur.setPrenom(prof.getPrenom());
                    professeur.setEmail(prof.getEmail());
                    return professeurRepository.save(professeur);
                })
                .orElseThrow(() -> new RuntimeException("The person does not exist"));
    }

    //Supprimer un professeur
    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }
}
