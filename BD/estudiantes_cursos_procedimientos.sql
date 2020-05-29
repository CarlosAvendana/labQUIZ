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
CREATE PROCEDURE PRC_DEL_ESTUDIANTE(id_ int(9)
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



