https://dev.mysql.com/downloads/

Download MySQL Community Server

�ltimas vers�es:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

DROP TABLE banco.animal;
DROP TABLE banco.consulta;
DROP TABLE banco.dono;
DROP TABLE banco.especie;

CREATE TABLE banco.dono (
  ID INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(30) NOT NULL,
  TELEFONE VARCHAR(30) NOT NULL,
  EMAIL VARCHAR(30) NOT NULL,
  ENDERECO VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE banco.especie (
  ID INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(30) NOT NULL,
  
  PRIMARY KEY (id)
);

CREATE TABLE banco.animal (
  ID INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(30) NOT NULL,
  DATA_NASC VARCHAR(30) NOT NULL,
  RACA VARCHAR(30) NOT NULL,
  dono_id INT NOT NULL,
  especie_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT DONO_ANIMAL_FK 
  FOREIGN KEY (dono_id)
  REFERENCES banco1.dono(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE,
  
  CONSTRAINT ESPECIE_ANIMAL_FK 
  FOREIGN KEY (especie_id)
  REFERENCES banco1.especie(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE banco.consulta (
  ID INT NOT NULL AUTO_INCREMENT,
  DATA_CONSULTA VARCHAR(30) NOT NULL,
  HORARIO VARCHAR(30) NOT NULL,
  PROTOCOLO VARCHAR(30) NOT NULL,
  animal_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT ANIMAL_CONSULTA_FK 
  FOREIGN KEY (animal_id)
  REFERENCES banco1.animal(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE
);



