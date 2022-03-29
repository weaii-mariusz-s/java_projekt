CREATE TABLE AUTOR ( 
  autor_id number(10) NOT NULL,
  imie varchar2(25) NOT NULL,
  nazwisko varchar2(25) NOT NULL,
  miasto varchar2(25), NOT NULL,
  data_urodzenia DATE, NOT NULL,
  data_smierci DATE,
  CONSTRAINT autor_pk PRIMARY KEY (autor_id)
);

CREATE TABLE OSOBA ( 
  osoba_id number(10) NOT NULL,
  imie varchar2(25) NOT NULL,
  nazwisko varchar2(25) NOT NULL,
  telefon varchar2(25), NOT NULL,
  email varchar2(25), NOT NULL,
  haslo varchar2(32), NOT NULL,
  CONSTRAINT osoba_pk PRIMARY KEY (osoba_id)
);

CREATE TABLE KATEGORIA ( 
  kategoria_id number(10) NOT NULL,
  nazwa_kategorii varchar2(25) NOT NULL,
  CONSTRAINT kategoria_pk PRIMARY KEY (kategoria_id)
);

CREATE TABLE WYDAWNICTWO ( 
  wydawnictwo_id number(10) NOT NULL,
  nazwa_wydawnictwa varchar2(50) NOT NULL,
  miasto varchar2(25),
  CONSTRAINT wydawnictwo_pk PRIMARY KEY (wydawnictwo_id)
);

CREATE TABLE STATUS_WYPOZYCZENIA ( 
  status_wyp_id number(10) NOT NULL,
  nazwa_statusu varchar2(25) NOT NULL,
  CONSTRAINT status_wyp_pk PRIMARY KEY (status_wyp_id)
);

CREATE TABLE STATUS_KSIAZKI ( 
  status_ks_id number(10) NOT NULL,
  nazwa_statusu varchar2(25) NOT NULL,
  CONSTRAINT status_ks_pk PRIMARY KEY (status_ks_id)
);
/////
CREATE TABLE LISTA_ZYCZEN ( 
  lista_zyczen_id number(10) NOT NULL,
  czytelnik_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  status_wyp_id number(10) NOT NULL,
  CONSTRAINT lista_zyczen_pk PRIMARY KEY (lista_zyczen_id)
);

CREATE TABLE KSIAZKA ( 
  ksiazka_id number(10) NOT NULL,
  tytul varchar2(50) NOT NULL,
  kategoria_id number(10) NOT NULL,
  wydawnictwo_id number(10) NOT NULL,
  rok_wydania DATE, NOT NULL,
  liczba_sztuk number(10) NOT NULL,
  CONSTRAINT autor_pk PRIMARY KEY (autor_id)
);

CREATE TABLE AUTOR_KSIAZKA ( 
  autor_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  FOREIGN KEY (autor_id) REFERENCES AUTOR(autor_id),
  FOREIGN KEY (ksiazka_id) REFERENCES KSIAZKA(ksiazka_id)
);

CREATE TABLE WYPOZYCZENIE ( 
  wypozyczenie_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  czytelnik_id number(10) NOT NULL,
  bibliotekarz_id number(10) NOT NULL,
  data_wypozyczenia TIMESTAMP(2), NOT NULL,
  termin_do_zwrotu TIMESTAMP(2), NOT NULL,
  termim_zwrotu TIMESTAMP(2), NOT NULL,
  oplata DECIMAL(3,2), NOT NULL,
  status_wyp_id number(10) NOT NULL,
  CONSTRAINT wypozyczenie_pk PRIMARY KEY (wypozyczenie_id)
);

CREATE TABLE CZYTELNIK ( 
  czytelnik_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  data_urodzenia DATE, NOT NULL,
  CONSTRAINT czytelnik_pk PRIMARY KEY (czytelnik_id)
);

CREATE TABLE BIBLIOTEKARZ ( 
  bibliotekarz_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  CONSTRAINT bibliotekarz_pk PRIMARY KEY (bibliotekarz_id)
);

CREATE TABLE ADMINISTRATOR ( 
  administrator_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  CONSTRAINT administrator_pk PRIMARY KEY (administrator_id)
);
