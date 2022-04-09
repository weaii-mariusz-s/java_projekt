CREATE TABLE AUTOR (
  autor_id number(10) NOT NULL,
  imie varchar2(30) NOT NULL,
  nazwisko varchar2(30) NOT NULL,
  miasto varchar2(30) NOT NULL,
  kraj varchar2(30) NOT NULL,
  data_urodzenia DATE NOT NULL,
  data_smierci DATE DEFAULT NULL,
  CONSTRAINT autor_pk PRIMARY KEY (autor_id)
);

CREATE TABLE KATEGORIA ( 
  kategoria_id number(10) NOT NULL,
  nazwa_kategorii varchar2(35) NOT NULL,
  rodzic_id number(10) DEFAULT NULL,
  CONSTRAINT kategoria_pk PRIMARY KEY (kategoria_id)
);

CREATE TABLE WYDAWNICTWO ( 
  wydawnictwo_id number(10) NOT NULL,
  nazwa_wydawnictwa varchar2(55) NOT NULL,
  miasto varchar2(30),
  CONSTRAINT wydawnictwo_pk PRIMARY KEY (wydawnictwo_id)
);

CREATE TABLE OSOBA ( 
  osoba_id number(10) NOT NULL,
  imie varchar2(30) NOT NULL,
  nazwisko varchar2(30) NOT NULL,
  telefon varchar2(12) NOT NULL,
  email varchar2(25) NOT NULL,
  haslo varchar2(32) NOT NULL,
  CONSTRAINT osoba_pk PRIMARY KEY (osoba_id)
);

CREATE TABLE CZYTELNIK ( 
  czytelnik_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  data_urodzenia DATE NOT NULL,
  CONSTRAINT czytelnik_pk PRIMARY KEY (czytelnik_id),
  CONSTRAINT czytelnik_osoba_fk FOREIGN KEY (osoba_id) REFERENCES OSOBA(osoba_id)
);

CREATE TABLE BIBLIOTEKARZ ( 
  bibliotekarz_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  CONSTRAINT bibliotekarz_pk PRIMARY KEY (bibliotekarz_id),
  CONSTRAINT bibliotekarz_osoba_fk FOREIGN KEY (osoba_id) REFERENCES OSOBA(osoba_id)
);

CREATE TABLE ADMINISTRATOR ( 
  administrator_id number(10) NOT NULL,
  osoba_id number(10) NOT NULL,
  CONSTRAINT administrator_pk PRIMARY KEY (administrator_id),
  CONSTRAINT administrator_osoba_fk FOREIGN KEY (osoba_id) REFERENCES OSOBA(osoba_id)
);

CREATE TABLE KSIAZKA ( 
  ksiazka_id number(10) NOT NULL,
  tytul varchar2(70) NOT NULL,
  kategoria_id number(10) NOT NULL,
  wydawnictwo_id number(10) NOT NULL,
  rok_wydania number(4) NOT NULL,
  liczba_sztuk number(10) NOT NULL,
  CONSTRAINT ksiazka_pk PRIMARY KEY (ksiazka_id),
  CONSTRAINT ksiazka_kategoria_fk FOREIGN KEY (kategoria_id) REFERENCES KATEGORIA(kategoria_id),
  CONSTRAINT ksiazka_wydawnictwo_fk FOREIGN KEY (wydawnictwo_id) REFERENCES WYDAWNICTWO(wydawnictwo_id)
);

CREATE TABLE AUTOR_KSIAZKA ( 
  autor_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  CONSTRAINT autor_ksiazka_autor_fk FOREIGN KEY (autor_id) REFERENCES AUTOR(autor_id),
  CONSTRAINT autor_ksiazka_ksiazka_fk FOREIGN KEY (ksiazka_id) REFERENCES KSIAZKA(ksiazka_id)
);

CREATE TABLE LISTA_ZYCZEN ( 
  lista_zyczen_id number(10) NOT NULL,
  czytelnik_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  nazwa_statusu varchar2(25) NOT NULL,
  CONSTRAINT lista_zyczen_pk PRIMARY KEY (lista_zyczen_id),
  CONSTRAINT lista_zyczen_czytelnik_fk FOREIGN KEY (czytelnik_id) REFERENCES CZYTELNIK(czytelnik_id),
  CONSTRAINT lista_zyczen_ksiazka_fk FOREIGN KEY (ksiazka_id) REFERENCES KSIAZKA(ksiazka_id)
);

CREATE TABLE WYPOZYCZENIE ( 
  wypozyczenie_id number(10) NOT NULL,
  ksiazka_id number(10) NOT NULL,
  czytelnik_id number(10) NOT NULL,
  bibliotekarz_id number(10) NOT NULL,
  data_wypozyczenia TIMESTAMP(2) NOT NULL,
  termin_do_zwrotu TIMESTAMP(2) NOT NULL,
  termim_zwrotu TIMESTAMP(2) NOT NULL,
  oplata DECIMAL(3,2) NOT NULL,
  nazwa_statusu varchar2(25) NOT NULL,
  CONSTRAINT wypozyczenie_pk PRIMARY KEY (wypozyczenie_id),
  CONSTRAINT wypozyczenie_ksiazka_fk FOREIGN KEY (ksiazka_id) REFERENCES KSIAZKA(ksiazka_id),
  CONSTRAINT wypozyczenie_czytelnik_fk FOREIGN KEY (czytelnik_id) REFERENCES CZYTELNIK(czytelnik_id),
  CONSTRAINT wypozyczenie_bibliotekarz_fk FOREIGN KEY (bibliotekarz_id) REFERENCES BIBLIOTEKARZ(bibliotekarz_id)
);