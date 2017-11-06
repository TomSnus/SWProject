/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.Student;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Jon
 */
@RequestScoped
public class StudierendenService {
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;
    @Transactional
    public Student immatrikulieren(Student student) {
        student.setMatrikelNr(3001545);
        
        
        entityManager.persist(student);
        return student;
    }
}
