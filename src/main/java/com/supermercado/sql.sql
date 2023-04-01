CREATE DATABASE supermercado
WITH
   ENCODING = 'UTF8'
   OWNER = postgres
   CONNECTION LIMIT = -1;

CREATE TABLE public.cargo (
	cargid int NOT NULL,
	cargnombre varchar (50) NOT NULL,
	PRIMARY KEY(cargid)
);

CREATE TABLE public.cliente (
	clieid serial NOT NULL,
	clienumeroidentificacion int NOT NULL,
	clienombre varchar(100) NOT NULL,
	clieapellido varchar(100) NOT NULL,
	cargid int NOT NULL,
	PRIMARY KEY(clieid),
	FOREIGN	KEY (cargid) REFERENCES public.cargo(cargid)
);

CREATE TABLE public.supermercado (
	supeid int NOT NULL,
	supenombre varchar (100) NOT NULL,
	PRIMARY KEY (supeid)
);

CREATE TABLE public.supermercadocliente (
	sepmecid int NOT NULL,
	supeid int NOT NULL,
	clieid int NOT NULL,
	PRIMARY KEY (supeid, clieid),
	FOREIGN KEY (supeid) REFERENCES public.supermercado(supeid),
	FOREIGN KEY (clieid) REFERENCES public.cliente(clieid)
);

INSERT INTO public.supermercado
(supeid, supenombre)
VALUES(1, 'Éxito'),
(2, 'Jumbo'),
(3, 'Olímpica');

INSERT INTO public.cargo
(cargid, cargnombre)
VALUES(1, 'Desarrollador de software'),
(2, 'Auxiliar de cartera'),
(3, 'Director operativo'),
(4, 'Independiente'),
(5, 'Comerciante');

SELECT * FROM cliente c1 JOIN cargo c2 on c1.cargid = c2.cargid WHERE c1.cargid = 4
