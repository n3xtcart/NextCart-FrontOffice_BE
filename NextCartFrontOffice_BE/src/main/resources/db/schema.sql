CREATE TABLE liste_spesa (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `data_prevista` DATE NOT NULL,
  `id_utente` BIGINT NOT NULL,
  UNIQUE KEY `unique_lista_per_utente` (`nome`,`id_utente`),
  PRIMARY KEY (`id`));
  
CREATE TABLE prodotti_lista_spesa (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_prodotto` BIGINT NOT NULL,
  `quantita` DECIMAL(10,2) NOT NULL,
  `id_tipologia` BIGINT NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `checked` BIT(1) NOT NULL DEFAULT 0,
  `lista_spesa_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lista_spesa`
    FOREIGN KEY (`lista_spesa_id`)
    REFERENCES `liste_spesa` (`id`)
    ON DELETE CASCADE);
