CREATE TABLE Formation (
   id VARCHAR PRIMARY KEY, -- Identifiant unique pour chaque formation
   name VARCHAR NOT NULL  UNIQUE  -- Nom de la formation, obligatoire
);

CREATE TABLE Student (
     id VARCHAR PRIMARY KEY,          -- Identifiant unique pour chaque étudiant
     name VARCHAR NOT NULL,           -- Nom de l'étudiant, obligatoire
     moyenne FLOAT CHECK (moyenne BETWEEN 0 AND 20), -- Moyenne entre 0 et 20
     formation_id VARCHAR,            -- Référence à la table Formation
     FOREIGN KEY (formation_id) REFERENCES Formation(id) -- Clé étrangère vers Formation
);


INSERT INTO Formation (id, name) VALUES
     ('F1', 'Genie Informatique'),
     ('F2', 'Genie des Systemes Reseaux'),
     ('F3', 'Genie des Sytemes Electroniques et Automatique'),
     ('F4', 'Genie de la Cyber Securite'),
     ('F5', 'Genie Eco Energetique');

INSERT INTO Student (id, name, moyenne, formation_id) VALUES
  ('S1', 'Ali Bouazza', 17.8, 'F1'),
  ('S2', 'Fatima Zahra', 14, 'F2'),
  ('S3', 'Youssef Amrani', 15, 'F3'),
  ('S4', 'Hanae Ait Oukdim', 07, 'F4'),
  ('S5', 'Khalid Benjelloun', 18.7, 'F1'),
  ('S6', 'Nadia El Mansouri', 17.9, 'F1'),
  ('S7', 'Said El Kharraz', 20, 'F2'),
  ('S8', 'Rachid Bounaim', 15.5, 'F4'),
  ('S9', 'Mounia Hamdi', 14.5, 'F3'),
  ('S10', 'Karima Boulkheir', 17.8, 'F1'),
  ('S11', 'Ahmed Chafiq', 14.5, 'F1'),
  ('S12', 'Laila Benkirane', 17.8, 'F2'),
  ('S13', 'Hamid Outaleb', 7.5, 'F3'),
  ('S14', 'Sara El Idrissi', 18.9, 'F4'),
  ('S15', 'Rania Boukhari', 20, 'F5'),
  ('S16', 'Imad Tazi', 17.2, 'F1'),
  ('S17', 'Nour El Yassir', 14.5, 'F1'),
  ('S18', 'Omar Alaoui', 14.5, 'F1'),
  ('S19', 'Soukaina Jabiri', 15.2, 'F2'),
  ('S20', 'Zakaria Farhane', 17.8, 'F3');
