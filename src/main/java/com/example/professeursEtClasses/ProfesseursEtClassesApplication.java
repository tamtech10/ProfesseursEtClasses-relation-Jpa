package com.example.professeursEtClasses;

import com.example.professeursEtClasses.model.Classe;
import com.example.professeursEtClasses.model.Professeur;
import com.example.professeursEtClasses.repository.ClasseRepository;
import com.example.professeursEtClasses.repository.ProfesseurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfesseursEtClassesApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProfesseursEtClassesApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ProfesseurRepository professeurRepository, ClasseRepository classeRepository) {
        return args -> {
            professeurRepository.save(new Professeur("Aumegeas", "Maxime", "maxime@gmail.com"));
            professeurRepository.save(new Professeur("Cukon", "Tamara", "tamara@gmail.com"));
            professeurRepository.save(new Professeur("Nfaidh", "Awatef", "awatef@gmail.com"));
            professeurRepository.save(new Professeur("Bare", "Esteban", "esteban@gmail.com"));


            Professeur Aumegeas = professeurRepository.findById(1L).get();
            Professeur Cukon = professeurRepository.findById(2L).get();
            Professeur Nfaidh = professeurRepository.findById(3L).get();
            Professeur Bare = professeurRepository.findById(4L).get();

            classeRepository.save(new Classe("classe1", "Bac", "Français", 23, Aumegeas));
            classeRepository.save(new Classe("classe2", "Bts", "Anglais", 19, Nfaidh));
            classeRepository.save(new Classe("classe3", "Bac","Mathématiques", 28, Cukon));
            classeRepository.save(new Classe("classe4", "Bac","Sport" , 22, Aumegeas));
            classeRepository.save(new Classe("classe5", "Bac", "Informatique", 17, Bare));
            classeRepository.save(new Classe("classe6", "Bts", "Histoire", 19, Cukon));
        };
    }


}











