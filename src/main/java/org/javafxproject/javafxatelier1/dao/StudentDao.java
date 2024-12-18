package org.javafxproject.javafxatelier1.dao;

import org.javafxproject.javafxatelier1.entities.Formation;
import org.javafxproject.javafxatelier1.entities.Student;
import org.javafxproject.javafxatelier1.utils.CRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface StudentDao extends CRUD<Student,String> {
    default List<Student> findByFormation(String formation) {
        List<Student> students = StudentDao.this.getAll(new Student());
        FormationDao formationDao = new FormationDao(){};
        String fid = formationDao.findByName(formation).getId();
        return students.stream()
                .filter(e->e.getFormation_id().equals(fid))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
