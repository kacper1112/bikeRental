# Bike Rental

## Główne założenia
Celem projektu jest stworzenie aplikacji umożliwiającej prowadzenie wypożyczalni rowerów i sprzętu rowerowego. Aplikacja
ma służyć zarówno obsłudze wypożyczalni, jak i klientom. Podstawowe funkcjonalności z perspektywy:
* administratora:
    * dodawanie nowego sprzętu do bazy,
    * dodawanie nowych klientów do bazy,
    * dodawanie nowych pracowników do bazy,
    * wyszukiwanie klientów po słowach kluczowych,
    * rejestracja wypożyczeń (jeden lub więcej rowerów i sztuk sprzętu na użytkownika),
    * rejestracja uszkodzeń sprzętu podczas wypożyczenia,
    * generowanie kodów zniżkowych,
    * generowanie raportów za dany okres działalności (przychody),
* klienta:
    * przeglądanie dostępnych rowerów i sprzętu,
    * składanie prośby o rezerwację,
    * korzystanie z kodów zniżkowych przy składaniu rezerwacji,
* dla każdego użytkownika:
    * wyszukiwanie sprzętu po słowach kluczowych, kategoriach.
    
    
## Implementacja
Nie planuję tworzenia graficznego interfejsu użytkownika, dostęp do programu będzie możliwy poprzez polecenia tekstowe. 

Projekt będzie zrealizowany w języku Java z pomocą bazy danych PostgreSQL. Dla ułatwienia prezentacji aplikacja zostanie
uruchomiona na zdalnym serwerze (wstępnie myślałem o Heroku).

## Wykorzystywane wzorce projektowe

1. `Builder` - klasy Bike, org.kacper.Customer — dodawanie opcjonalnych informacji.
1. `Strategy` - wybieranie odpowiedniego sposobu wyliczania należności za wynajem (czy rower + akcesoria, czy samo
akcesorium, czy obowiązuje zniżka, czy wypożyczenie w święto itd.)
1. `Factory` - połączenie z bazą danych (łatwe przejście pomiędzy np. bazą testową w H2 a produkcyjną w PostgreSQL,
 bazą zapasową w SQLite itp.)
1. `Singleton` 
    * głowna klasa programu przechowująca informacje np. o aktualnym użytkowniku (*sesja* programu),
    * w implementacji połączenia z bazą danych,
1. `Facade` - ograniczenie dostępnych instrukcji dla różnych grup użytkowników (admin całość, pracownik mniej, klient 
najmniej),
1. `Adapter` - generowanie raportów do różnych formatów plików, wykorzystanie zewnętrznych bibliotek i dopasowanie ich
do jednego interfejsu,

---------------------
#### Pomysły:
2. Dodać `Observer` do ustawiania odpowiedniego kalkulatora ceny w zamówieniu (w zależności od liczby
wypożyczonych rzeczy, numeru wizyty itd.)