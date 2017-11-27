/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.Student;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Tom
 */
@RequestScoped
public class StudierendenService {
    Random rng = new Random();
    
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;
    @Transactional
    public Student immatrikulieren(Student student) {
        student.setMatrikelNr(rng.nextInt());
        
        
        entityManager.persist(student);
        return student;
    }
}
