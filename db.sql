DROP SCHEMA IF EXISTS dat107_innlevering_3 CASCADE;
CREATE SCHEMA dat107_innlevering_3;
SET search_path TO dat107_innlevering_3;

CREATE TABLE ansatt (
    id             SERIAL PRIMARY KEY,
    brukernavn     VARCHAR(30) UNIQUE NOT NULL,
    fornavn        VARCHAR(30) NOT NULL,
    etternavn      VARCHAR(30) NOT NULL,
    ansettelsedato DATE        NOT NULL DEFAULT CURRENT_DATE,
    stilling       VARCHAR(30) NOT NULL,
    manedslonn     INTEGER     NOT NULL,
    avdeling_id    INTEGER NOT NULL REFERENCES avdeling ON DELETE RESTRICT
);

CREATE TABLE avdeling (
    id   SERIAL PRIMARY KEY,
    navn VARCHAR(30) UNIQUE NOT NULL
);

ALTER TABLE avdeling ADD COLUMN sjef_id INTEGER UNIQUE;

ALTER TABLE avdeling
    ADD CONSTRAINT avdeling_sjef_fk
        FOREIGN KEY (sjef_id)
            REFERENCES ansatt(id)
            ON DELETE RESTRICT;

INSERT INTO avdeling (navn) VALUES
    ('IT'),
    ('Salg');

INSERT INTO ansatt (brukernavn, fornavn, etternavn, stilling, manedslonn, avdeling_id) VALUES
    ('lhp', 'Lars', 'Hansen', 'Avdelingsleder IT', 85000, 1),
    ('mab', 'Mari', 'Berg', 'Systemutvikler', 62000, 1),
    ('kai', 'Kari', 'Iversen', 'Testleder', 58000, 1),
    ('sjo', 'Svein', 'Johansen', 'Salgsdirektør', 90000, 2),
    ('ema', 'Erik', 'Martinsen', 'Salgskonsulent', 55000, 2);

UPDATE avdeling SET sjef_id = (SELECT id FROM ansatt WHERE brukernavn = 'lhp') WHERE id = 1;
UPDATE avdeling SET sjef_id = (SELECT id FROM ansatt WHERE brukernavn = 'sjo') WHERE id = 2;

SELECT * FROM ansatt;

SELECT * FROM avdeling;
