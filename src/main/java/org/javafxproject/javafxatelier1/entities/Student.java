package org.javafxproject.javafxatelier1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javafxproject.javafxatelier1.dao.FormationDao;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String id;
    private String name;
    private double moyenne;
    private String formation_id;
}
