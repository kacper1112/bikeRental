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

1. `Builder` - klasy Bike, Customer i Rental.
1. `Singleton`
1. `Factory`
1. `Facade`
1. `Strategy`
