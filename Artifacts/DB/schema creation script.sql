-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema e3Learning
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema e3Learning
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e3Learning` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `e3Learning` ;

-- -----------------------------------------------------
-- Table `e3Learning`.`Countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e3Learning`.`Countries` (
  `id` INT NOT NULL,
  `code` VARCHAR(2) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e3Learning`.`Addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e3Learning`.`Addresses` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `streetName` VARCHAR(255) NOT NULL,
  `suburb` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Address_country_idx` (`country_id` ASC),
  CONSTRAINT `fk_Address_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `e3Learning`.`Countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e3Learning`.`Accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e3Learning`.`Accounts` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 0,
  `address_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Account_Address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_Account_Address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `e3Learning`.`Addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e3Learning`.`Courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e3Learning`.`Courses` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(1024) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e3Learning`.`Training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e3Learning`.`Training` (
  `course_id` BIGINT(20) NOT NULL,
  `account_id` BIGINT(20) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NULL,
  `grade` INT NULL,
  PRIMARY KEY (`course_id`, `account_id`),
  INDEX `fk_Course_has_Account_Account1_idx` (`account_id` ASC),
  INDEX `fk_Course_has_Account_Course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_Course_has_Account_Course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `e3Learning`.`Courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Course_has_Account_Account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `e3Learning`.`Accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
