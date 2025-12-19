package com.example.professeursEtClasses.service;

import com.example.professeursEtClasses.model.Classe;
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
        public void deleteProfesseur (Long id){
            professeurRepository.deleteById(id);
        }


    //Statistiques
    //Nombre total de classes
    public int nbTotalClasses(Long id) {
        Professeur nbClasseProf = getProfesseurById(id);
        return nbClasseProf.getClasses().size();
    }

    //Nombre total d'élèves par professeur
    public int nbTotalEleves(Long id) {
        int count = 0;
        Professeur nbElveProf = getProfesseurById(id);
        for(Classe classe : nbElveProf.getClasses())
        {
            count += classe.getNombreEleves();
        } return count;
    }

    //la moyenne d'élèves par classe pour un professeur
    public int moyenneEleves(Long id) {
        Professeur professeur = getProfesseurById(id);
        if (professeur.getClasses().isEmpty()) {
            return 0;
        }
        int totalEleves = 0;
        for (Classe classe : professeur.getClasses()) {
            totalEleves += classe.getNombreEleves();
        }
        return totalEleves / professeur.getClasses().size();
    }


}

