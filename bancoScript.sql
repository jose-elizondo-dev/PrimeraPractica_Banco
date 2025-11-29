CREATE DATABASE IF NOT EXISTS banco;
USE banco;

CREATE TABLE IF NOT EXISTS Administrador (
    cedula VARCHAR(20) PRIMARY KEY,
    nombreCompleto VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Cliente (
    cedula VARCHAR(20) PRIMARY KEY,
    nombreCompleto VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    sexo CHAR(1),
    profesion VARCHAR(100),
    direccion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Cuenta (
    numeroCuenta VARCHAR(20) PRIMARY KEY,
    tipo ENUM('AHORRO', 'DEBITO', 'CREDITO') NOT NULL,
    saldo DECIMAL(15,2) NOT NULL,
    fechaCreacion DATE NOT NULL,
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    cedulaCliente VARCHAR(20) NOT NULL,
    FOREIGN KEY (cedulaCliente) REFERENCES Cliente(cedula)
);

CREATE TABLE IF NOT EXISTS CuentaAhorro (
    numeroCuenta VARCHAR(20) PRIMARY KEY,
    porcentajeInteres DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (numeroCuenta) REFERENCES Cuenta(numeroCuenta)
);

CREATE TABLE IF NOT EXISTS CuentaDebito (
    numeroCuenta VARCHAR(20) PRIMARY KEY,
    porcentajeInteres DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (numeroCuenta) REFERENCES Cuenta(numeroCuenta)
);

CREATE TABLE IF NOT EXISTS CuentaCredito (
    numeroCuenta VARCHAR(20) PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    limiteCredito DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (numeroCuenta) REFERENCES Cuenta(numeroCuenta)
);