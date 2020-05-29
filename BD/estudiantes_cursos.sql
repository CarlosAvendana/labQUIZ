-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estudiantes_curso
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estudiantes_curso
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estudiantes_curso` DEFAULT CHARACTER SET utf8 ;
USE `estudiantes_curso` ;

-- -----------------------------------------------------
-- Table `estudiantes_curso`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estudiantes_curso`.`estudiante` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `edad` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estudiantes_curso`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estudiantes_curso`.`curso` (
  `idcurso` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `creditos` INT NULL,
  `estudiante_id` INT NOT NULL,
  PRIMARY KEY (`idcurso`),
  INDEX `fk_curso_estudiante_idx` (`estudiante_id` ASC) VISIBLE,
  CONSTRAINT `fk_curso_estudiante`
    FOREIGN KEY (`estudiante_id`)
    REFERENCES `estudiantes_curso`.`estudiante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
