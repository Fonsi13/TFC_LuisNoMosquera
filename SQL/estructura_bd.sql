CREATE DATABASE IF NOT EXISTS snaplabs;
USE snaplabs;

CREATE TABLE usuario (
	uuid CHAR(36) PRIMARY KEY,
	correo VARCHAR(255) NOT NULL,
	username VARCHAR(25) NOT NULL,
	password VARCHAR(255) NOT NULL,
	foto VARCHAR(255) NOT NULL,
	rol ENUM('ADMIN', 'USER') NOT NULL,
	fecha_creacion date DEFAULT CURRENT_DATE,
	UNIQUE (username)
);

CREATE TABLE mazo (
	id INT AUTO_INCREMENT PRIMARY KEY,
	contenido VARCHAR(255) NOT NULL,
	nombre VARCHAR(25) NOT NULL,
	descripcion TEXT DEFAULT '',
	arquetipo VARCHAR(255) DEFAULT '',
	publico BOOLEAN NOT NULL,
	fecha_creacion date DEFAULT CURRENT_DATE,
	id_usuario CHAR(36) NOT NULL,
	CONSTRAINT fk_id_usuario_mazo FOREIGN KEY (id_usuario) REFERENCES usuario(uuid) ON DELETE CASCADE
);

CREATE TABLE variante (
	uuid CHAR(36) PRIMARY KEY,
	nombre VARCHAR(25) NOT NULL,
	descripcion VARCHAR(255) DEFAULT '',
	urlImagen VARCHAR(255) NOT NULL,
	personaje VARCHAR(25) NOT NULL,
	fecha_creacion date DEFAULT CURRENT_DATE,
	id_usuario CHAR(36) NOT NULL,
	CONSTRAINT fk_id_usuario_variante FOREIGN KEY (id_usuario) REFERENCES usuario(uuid) ON DELETE CASCADE
);

CREATE TABLE usuario_favorito_mazo (
	id_usuario CHAR(36),
	id_mazo INT,
	CONSTRAINT pk_usuario_favorito_mazo PRIMARY KEY (id_usuario,id_mazo),
	CONSTRAINT fk_favorito_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(uuid) ON DELETE CASCADE,
	CONSTRAINT fk_favorito_id_mazo FOREIGN KEY (id_mazo) REFERENCES mazo(id) ON DELETE CASCADE
);

CREATE TABLE usuario_like_variante (
	id_usuario CHAR(36),
	id_variante CHAR(36),
	CONSTRAINT pk_usuario_like_variante PRIMARY KEY (id_usuario,id_variante),
	CONSTRAINT fk_like_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(uuid) ON DELETE CASCADE,
	CONSTRAINT fk_like_id_variante FOREIGN KEY (id_variante) REFERENCES variante(uuid) ON DELETE CASCADE
);

CREATE TABLE carta (
	clave VARCHAR(50) PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	coste INT NOT NULL,
	poder INT NOT NULL,
	serie VARCHAR(20) NOT NULL,
	habilidades VARCHAR(100),
	imagen VARCHAR(255) NOT NULL,
	id_snap VARCHAR(50) NOT NULL
);