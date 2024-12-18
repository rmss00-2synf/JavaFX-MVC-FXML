package org.javafxproject.javafxatelier1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javafxproject.javafxatelier1.dao.FormationDao;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Formation {
    private String id;
    private String name;
    private List<Student> relationstudents;
    public float getMoyenne(){
        float moyenne = 0;
        FormationDao formationDao = new FormationDao() {};
        relationstudents = formationDao.findAllStudents(id);
        for (Student student : relationstudents) {
            moyenne += (float) student.getMoyenne();
        }
        return moyenne/relationstudents.size();
    }
}
