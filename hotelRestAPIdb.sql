CREATE DATABASE hotelesdb;
USE hotelesdb;

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` float(2,2) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `pais` varchar(20) DEFAULT NULL,
  `estrellas` int(11) DEFAULT NULL,
  `descripcion` blob,
  `imagen` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

INSERT INTO `hotel`VALUES(0,"Hotel M.Rajoy",125,"Calle del PP, numero 105","Madrid",2,"Resort creado por M.Rajoy, para el disfrute de los pperianos y su partido. No hay mejor sitio para descansar despues de una buena xarla politica. Ofrecemos salas exclusivas para politicos.","default.png")
