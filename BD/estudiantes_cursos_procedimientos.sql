use estudiantes_curso;

DROP PROCEDURE IF EXISTS PRC_INS_ESTUDIANTE;

DELIMITER //
CREATE PROCEDURE PRC_INS_ESTUDIANTE(id_ int(9), nombre_ varchar(50),apellido_ varchar(100),edad_ int(2))
BEGIN
	insert into estudiantes_curso.estudiante (id, nombre, apellido,edad) 
	values (id_,nombre_,apellido_,edad_);
END
//
DELIMITER ;


DROP PROCEDURE IF EXISTS PRC_UPD_ESTUDIANTE;

DELIMITER //
CREATE PROCEDURE PRC_UPD_ESTUDIANTE(id_ int(9), nombre_ varchar(50),apellido_ varchar(100),edad_ int(2))
BEGIN
	update estudiantes_curso.estudiante
	set nombre=nombre_,apellido=apellido_,
	edad=edad_
	where id=id_;
END
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_DEL_ESTUDIANTE;

DELIMITER //
CREATE PROCEDURE PRC_DEL_ESTUDIANTE(id_ int(9))
BEGIN 
	delete from estudiantes_curso.estudiante 
	where id=id_;
END
//
DELIMITER ;


DROP PROCEDURE IF EXISTS PRC_OBTIENE_UN_ESTUDIANTE;

DELIMITER //
CREATE PROCEDURE PRC_OBTIENE_UN_ESTUDIANTE(id_ int(9))
BEGIN
	SELECT id, nombre, apellido, edad FROM estudiantes_curso.estudiante 
	where id=id_;
END 
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_OBTIENE_ESTUDIANTES;

DELIMITER //
CREATE PROCEDURE PRC_OBTIENE_ESTUDIANTES()
BEGIN
	SELECT id, nombre, apellido, edad FROM estudiantes_curso.estudiante;
END 
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_OBTIENE_CURSOS;
DELIMITER //
CREATE PROCEDURE PRC_OBTIENE_CURSOS()
BEGIN
	SELECT idcurso,descripcion,creditos FROM estudiantes_curso.curso;
END 
//
DELIMITER ;
<<<<<<< Updated upstream

DROP PROCEDURE IF EXISTS PRC_INS_MATRICULA;
DELIMITER //
CREATE PROCEDURE PRC_INS_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN
	INSERT INTO estudiantes_curso.matricula(estudiante_id, curso_idcurso) values (idEst_,idCurs_);
END
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_UPD_MATRICULA;
DELIMITER //
CREATE PROCEDURE PRC_UPD_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN
	update estudiantes_curso.matricula
	set estudiante_id=idEst_,curso_idcurso=idCurs_
	where estudiante_id=idEst_ AND curso_idcurso=idCurs_;
END
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_DEL_MATRICULA;

DELIMITER //
CREATE PROCEDURE PRC_DEL_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN 
	delete from estudiantes_curso.matricula 
	where estudiante_id=idEst_ AND curso_idcurso=idCurs_;
END
//
DELIMITER ;








=======
>>>>>>> Stashed changes

DROP PROCEDURE IF EXISTS PRC_INS_MATRICULA;
DELIMITER //
CREATE PROCEDURE PRC_INS_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN
	INSERT INTO estudiantes_curso.matricula(estudiante_id, curso_idcurso) values (idEst_,idCurs_);
END
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_UPD_MATRICULA;
DELIMITER //
CREATE PROCEDURE PRC_UPD_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN
	update estudiantes_curso.matricula
	set estudiante_id=idEst_,curso_idcurso=idCurs_
	where estudiante_id=idEst_ AND curso_idcurso=idCurs_;
END
//
DELIMITER ;

DROP PROCEDURE IF EXISTS PRC_DEL_MATRICULA;

DELIMITER //
CREATE PROCEDURE PRC_DEL_MATRICULA(idEst_ int(9),idCurs_ int(11))
BEGIN 
	delete from estudiantes_curso.matricula 
	where estudiante_id=idEst_ AND curso_idcurso=idCurs_;
END
//
DELIMITER ;


DROP PROCEDURE IF EXISTS PRC_OBTIENE_MATRICULA_ESTUDIANTE;

DELIMITER //
CREATE PROCEDURE PRC_OBTIENE_MATRICULA_ESTUDIANTE(idEst_ int(9))
BEGIN
	SELECT estudiante_id, curso_idcurso FROM estudiantes_curso.estudiante 
	where estudiante_id=idEst_;
END 
//
DELIMITER ;