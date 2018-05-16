DROP DATABASE IF EXISTS hotelesdb;
CREATE DATABASE hotelesdb;
USE hotelesdb;

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `pais` varchar(20) DEFAULT NULL,
  `estrellas` int(11) DEFAULT NULL,
  `descripcion` blob,
  `imagen` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

INSERT INTO `hotel`VALUES(0,"Hotel M.Rajoy",125,"Calle del PP, numero 105","Madrid",2,"Resort creado por M.Rajoy, para el disfrute de los pperianos y su partido. No hay mejor sitio para descansar despues de una buena xarla politica. Ofrecemos salas exclusivas para politicos.","default.png");
INSERT INTO `hotel`VALUES(1,"Hotel M.Rajoy",250,"Calle del PSOE, numero 105","España",5,"Resort creado por M. Rajoy, para el disfrute de los PPerianos y su organización. No hay mejor sitio para descansar despues de una buena charla a través de televisores de plasma. Ofrecemos salas exclusivas para mafiosos.","hotel6.jpg");
INSERT INTO `hotel`VALUES(2,"Hotel St.Esteve de les Roures",170,"Carrer Aguas Torcidas, numero 666","Catalunya",4,"Uno de los mejores establecimientos en St. Esteve de les Roures, este lugar recomforta hasta las propias cuentas de Twitter.","hotel2.jpg");
INSERT INTO `hotel`VALUES(3,"Hotel Daruma",144,"Carrer Llavina, numero 16","Catalunya",3,"Este hotel fue ideado por un par de empresarios Argentinos que buscaban darle un toque bien chevere a la terra catalana.","hotel1.jpg");
INSERT INTO `hotel`VALUES(4,"Hotel Light Turner",300,"Red Pill Street, numero 9","Inglaterra",5,"Este hotel fabricado en territorio anglosajón excede todas las expectativas posibles. Posee una de las terrazas más bellas de todo el país.","hotel3.jpg");
INSERT INTO `hotel`VALUES(5,"Hotel Cazzo",180,"Autostrada dei Fiori, numero 11","Italia",4,"A pesar de su nombre vulgar, este hotel no decepciona y está bastante aceptable en cuanto a calidad-precio. Altamente recomendable.","hotel4.jpg");
INSERT INTO `hotel`VALUES(6,"Hotel Estrujhenbajehn",275,"Ruhrtangente, numero 37","Alemania",5,"Simplemente insuperable si el propósito es pasar los días en territorio alemán. No existe ningún otro hotel que ofrezca lujos parecidos.","hotel5.jpg");
