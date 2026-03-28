DROP SCHEMA IF EXISTS dat107_innlevering_3 CASCADE;
CREATE SCHEMA dat107_innlevering_3;
SET search_path TO dat107_innlevering_3;

CREATE TABLE avdeling (
    id   SERIAL PRIMARY KEY,
    navn VARCHAR(30) UNIQUE NOT NULL
);

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

ALTER TABLE avdeling ADD COLUMN sjef_id INTEGER UNIQUE;
ALTER TABLE avdeling ADD CONSTRAINT avdeling_sjef_fk FOREIGN KEY (sjef_id) REFERENCES ansatt(id) ON DELETE RESTRICT;

CREATE TABLE prosjekt (
    id          SERIAL PRIMARY KEY,
    navn        VARCHAR(30) NOT NULL,
    beskrivelse TEXT
);

CREATE TABLE prosjekt_deltagelse(
    prosjekt_id INTEGER REFERENCES prosjekt ON DELETE RESTRICT,
    ansatt_id   INTEGER REFERENCES ansatt ON DELETE RESTRICT,
    rolle       VARCHAR(30),
    timer       INTEGER CHECK (timer >= 0),
    PRIMARY KEY (prosjekt_id, ansatt_id)
);

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

INSERT INTO prosjekt (navn, beskrivelse) VALUES
    ('Nettbutikk 2.0', 'Ny selvbetjeningsportal for B2C-kunder med betalingsløsning og lagerintegrasjon.'),
    ('CRM-overgang', 'Migrering fra gamle Excel-lister til moderne CRM-system for salgsavdelingen.');

WITH data AS (
    SELECT * FROM (VALUES
        ('lhp', 'Nettbutikk 2.0',   'Prosjektleder',     125),
        ('mab', 'Nettbutikk 2.0',   'Full-stack utvikler', 200),
        ('kai', 'Nettbutikk 2.0',   'Testkoordinator',   80),
        ('sjo', 'CRM-overgang',     'Sponsor / bestiller', 30),
        ('ema', 'CRM-overgang',     'Superbruker / test',  60)
    ) AS t(bruker, prosjekt, rolle, timer)
)

INSERT INTO prosjekt_deltagelse(prosjekt_id, ansatt_id, rolle, timer)
SELECT p.id, a.id, d.rolle, d.timer
FROM data d
JOIN ansatt a ON a.brukernavn = d.bruker
JOIN prosjekt p ON p.navn = d.prosjekt;

SELECT * FROM ansatt;
SELECT * FROM avdeling;
SELECT * FROM prosjekt;
SELECT * FROM prosjekt_deltagelse;
