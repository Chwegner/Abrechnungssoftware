drop table auftrag;
drop table auftrag_intervall;
drop table kunden;
drop table rechnung;
drop table rechnungen;
drop table stammdaten;

create table if not exists auftrag
(
  id        int(11) auto_increment
    primary key,
  kunden_id int(11)      not null,
  von       varchar(20)  not null,
  bis       varchar(20)  not null,
  grund     varchar(250) not null
)
  engine = MyISAM
  charset = utf8;

create table if not exists auftrag_intervall
(
  id             int(11) auto_increment
    primary key,
  auftrag_id     int(11)                            not null,
  von            varchar(20)                        not null,
  bis            varchar(20)                        not null,
  tage           int(2)                             null,
  re_erstellt    enum ('ja', 'nein') default 'nein' not null,
  re_bezahlt     enum ('ja', 'nein') default 'nein' not null,
  korrektur_tage int(2)                             null
)
  engine = MyISAM 
  charset = utf8;

create table if not exists kunden
(
  id          int(11) auto_increment
    primary key,
  firma       varchar(100)                         not null,
  anrede      enum ('Herr', 'Frau') default 'Herr' not null,
  vorname     varchar(100)                         not null,
  nachname    varchar(100)                         not null,
  strasse     varchar(50)                          not null,
  hsnr        varchar(20)                          not null,
  plz         varchar(20)                          not null,
  ort         varchar(50)                          not null,
  telefon     varchar(50)                          not null,
  telefax     varchar(50)                          not null,
  web         varchar(50)                          not null,
  email       varchar(50)                          not null,
  ap_anrede   enum ('Herr', 'Frau') default 'Herr' not null,
  ap_vorname  varchar(50)                          not null,
  ap_nachname varchar(50)                          not null,
  ap_telefon  varchar(50)                          not null,
  ap_email    varchar(50)                          not null,
  stdsatz     decimal(10, 2)                       not null
)
  engine = MyISAM
  charset = utf8;

create table if not exists rechnung
(
  id         int(11)     not null,
  auftrag_id int(11)     not null,
  re_nr      varchar(10) not null
)
  engine = MyISAM
  charset = utf8;

create table if not exists rechnungen
(
  id                   int(11) auto_increment
    primary key,
  grund                varchar(100) charset latin1 null,
  kunden_id            int(11)                     null,
  auftrag_id           int(11)                     null,
  von                  varchar(20) charset latin1  null,
  bis                  varchar(20) charset latin1  null,
  auftrag_intervall_id int(11)                     null,
  intervall_von        varchar(20) charset latin1  null,
  intervall_bis        varchar(20) charset latin1  null,
  tage                 int(3)                      null,
  korrektur_tage       int(2)                      null
)
  engine = MyISAM 
  charset = utf8;

create table if not exists stammdaten
(
  id           tinyint(1) auto_increment
    primary key,
  firma        varchar(100) not null,
  vorname      varchar(50)  not null,
  nachname     varchar(50)  not null,
  strasse      varchar(100) not null,
  hsnr         varchar(20)  not null,
  plz          varchar(10)  not null,
  ort          varchar(50)  not null,
  telefon      varchar(50)  not null,
  telefax      varchar(50)  not null,
  web          varchar(50)  not null,
  email        varchar(50)  not null,
  bankname     varchar(50)  not null,
  kontoinhaber varchar(50)  not null,
  bic          varchar(50)  not null,
  iban         varchar(50)  not null,
  steuernummer varchar(50)  not null
)
  engine = MyISAM
  charset = utf8;
  
INSERT INTO stammdaten (" +
                "firma, vorname, nachname, strasse, hsnr, plz, ort, telefon, telefax, web, email, bankname," +
                " kontoinhaber, bic, iban, steuernummer) VALUES (" +
                "'','','','','','','','','','','','','','','','');