DROP SCHEMA IF EXISTS `testing` ;

CREATE SCHEMA IF NOT EXISTS `testing` DEFAULT CHARACTER SET utf8 ;
USE `testing` ;

DROP TABLE IF EXISTS `testing`.`Answer` ;

CREATE TABLE IF NOT EXISTS `testing`.`Answer` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `string` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`Question` ;

CREATE TABLE IF NOT EXISTS `testing`.`Question` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `string` VARCHAR(45) NOT NULL,
    UNIQUE (`string`),
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`Question_has_Answer` ;

CREATE TABLE IF NOT EXISTS `testing`.`Question_has_Answer` (
    `Question_id` INT NOT NULL,
    `Answer_id` INT NOT NULL,
    PRIMARY KEY (`Question_id`, `Answer_id`),
    INDEX `fk_Question_has_Answer_Answer1_idx` (`Answer_id` ASC) VISIBLE,
    INDEX `fk_Question_has_Answer_Question1_idx` (`Question_id` ASC) VISIBLE,
    CONSTRAINT `fk_Question_has_Answer_Question1`
    FOREIGN KEY (`Question_id`)
    REFERENCES `testing`.`Question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_Question_has_Answer_Answer1`
    FOREIGN KEY (`Answer_id`)
    REFERENCES `testing`.`Answer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`Test` ;

CREATE TABLE IF NOT EXISTS `testing`.`Test` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `subject` VARCHAR(45) NOT NULL,
    `difficulty` INT NOT NULL,
    `time` TIME NOT NULL,
    `queries` INT NOT NULL,
    UNIQUE (`name`),
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`Test_has_Question` ;

CREATE TABLE IF NOT EXISTS `testing`.`Test_has_Question` (
    `Test_id` INT NOT NULL,
    `Question_id` INT NOT NULL,
    PRIMARY KEY (`Test_id`, `Question_id`),
    INDEX `fk_Test_has_Question_Question1_idx` (`Question_id` ASC) VISIBLE,
    INDEX `fk_Test_has_Question_Test1_idx` (`Test_id` ASC) VISIBLE,
    CONSTRAINT `fk_Test_has_Question_Test1`
    FOREIGN KEY (`Test_id`)
    REFERENCES `testing`.`Test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_Test_has_Question_Question1`
    FOREIGN KEY (`Question_id`)
    REFERENCES `testing`.`Question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`User` ;

CREATE TABLE IF NOT EXISTS `testing`.`User` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    UNIQUE (`login`),
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `testing`.`User_has_Test` ;
CREATE TABLE IF NOT EXISTS `testing`.`User_has_Test` (
    `User_id` INT NOT NULL,
    `Test_id` INT NOT NULL,
    `result` DOUBLE NOT NULL, PRIMARY KEY (`User_id`, `Test_id`),
    INDEX `fk_User_has_Test_Test1_idx` (`Test_id` ASC) VISIBLE,
    INDEX `fk_User_has_Test_User_idx` (`User_id` ASC) VISIBLE,
    CONSTRAINT `fk_User_has_Test_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `testing`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_User_has_Test_Test1`
    FOREIGN KEY (`Test_id`)
    REFERENCES `testing`.`Test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;