DROP SCHEMA IF EXISTS dat107_innlevering_3 CASCADE;
CREATE SCHEMA dat107_innlevering_3;
SET search_path TO dat107_innlevering_3;

CREATE TABLE ansatt (
    ansatt_id      SERIAL PRIMARY KEY,
    brukernavn     VARCHAR(30) UNIQUE NOT NULL,
    fornavn        VARCHAR(30) NOT NULL,
    etternavn      VARCHAR(30) NOT NULL,
    ansettelsedato DATE        NOT NULL,
    stilling       VARCHAR(30) NOT NULL,
    manedslonn     INTEGER     NOT NULL
);

INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansettelsedato, stilling, manedslonn)
VALUES
    ('ola12', 'Ola', 'Nordmann', CURRENT_DATE, 'Utvikler', 55000),
    ('kari34', 'Kari', 'Nordmann', CURRENT_DATE, 'Sjef', 70000);

SELECT * from ansatt