package org.javafxproject.javafxatelier1.dao;

import org.javafxproject.javafxatelier1.entities.Formation;
import org.javafxproject.javafxatelier1.entities.Student;
import org.javafxproject.javafxatelier1.utils.CRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface FormationDao extends CRUD<Formation,String> {

    default Formation findByName(String name) {
        List<Formation> formations = FormationDao.this.getAll(new Formation());
        return formations.stream()
                .filter(e->e.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new)).get(0);
    }
    default List<Student> findAllStudents(String fID) {
        StudentDao studentDao = new StudentDao() {};
        FormationDao formationDao = new FormationDao() {};
        Formation formation = FormationDao.this.findByName(fID);
        return studentDao.findByFormation(formation.getName());

    }
}
