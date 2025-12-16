package com.example.professeursEtClasses.service;

import com.example.professeursEtClasses.model.Classe;
import com.example.professeursEtClasses.repository.ClasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    //Lister toutes les classes
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    //Obtenir une classe par son ID
    public  Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    //Créer une nouvelle classe
    public Classe createClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    //Modifier une classe
    public Class updateClasse(Classe c) {
        return classeRepository.findById(c.getId())
                .map(classe -> {
                    classe.setNom(c.getNom());
                    classe.setNiveau(c.getNiveau());
                    classe.setMatiere(c.getMatiere());
                    classe.setNombreEleves(c.getNombreEleves());
                    return classeRepository.save(classe);
                })
                .orElseThrow(() -> new RuntimeException("The class does not exist")).getClass();
    }

    //Supprimer une classe
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }
}
