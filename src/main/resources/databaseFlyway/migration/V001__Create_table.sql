CREATE TABLE IF NOT EXISTS `buildings` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `apartaments` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `apartament` INT NOT NULL,
  `entrance` TINYINT(1) NOT NULL,
  `floor` TINYINT(1) NOT NULL,
  `square` FLOAT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `participant_OSBB` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FIO` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `data_of_birth` DATE NULL,
  PRIMARY KEY (`ID`),
  FULLTEXT INDEX `FIO` (`FIO`) VISIBLE)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `resident` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `entry_for_ap` TINYINT NOT NULL DEFAULT 0,
  `apartament_ID` INT NULL,
  `participant_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_kv_idx` (`apartament_ID` ASC) VISIBLE,
  INDEX `fk_ucha_idx` (`participant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_kv`
    FOREIGN KEY (`apartament_ID`)
    REFERENCES `apartaments` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ucha`
    FOREIGN KEY (`participant_ID`)
    REFERENCES `participant_OSBB` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `role` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `participant_ID` INT NOT NULL,
  `role` ENUM('participant', 'worker', 'management member', 'president') NOT NULL DEFAULT 'participant',
  INDEX `participant_ID_idx` (`ID` ASC) INVISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `resident`
    FOREIGN KEY (`participant_ID`)
    REFERENCES `participant_OSBB` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `building_for_apartament` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `building_ID` INT NOT NULL,
  `apartament_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `buildings_idx` (`building_ID` ASC) VISIBLE,
  INDEX `apartament_idx` (`apartament_ID` ASC) VISIBLE,
  CONSTRAINT `buildings`
    FOREIGN KEY (`building_ID`)
    REFERENCES `buildings` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `apartament`
    FOREIGN KEY (`apartament_ID`)
    REFERENCES `apartaments` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;