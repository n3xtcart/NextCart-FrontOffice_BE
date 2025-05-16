CREATE TABLE 'liste_spesa' (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `data_prevista` DATE NULL,
  `id_utente` INT NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `prodotti_lista_spesa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_prodotto` INT NOT NULL,
  `quantita` DECIMAL(10,2) NOT NULL,
  `id_tipologia` INT NOT NULL,
  `note` VARCHAR(255) NULL,
  `checked` TINYINT NULL DEFAULT 0,
  `lista_spesa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lista_spesa`
    FOREIGN KEY (`lista_spesa_id`)
    REFERENCES `liste_spesa` (`id`)
    ON DELETE CASCADE);
