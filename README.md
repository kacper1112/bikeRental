# Bike Rental

## Główne założenia
Celem projektu jest stworzenie aplikacji umożliwiającej prowadzenie wypożyczalni rowerów i sprzętu rowerowego. Aplikacja
ma służyć zarówno obsłudze wypożyczalni, jak i klientom. Podstawowe funkcjonalności z perspektywy:
* administratora:
    * dodawanie nowych pracowników do bazy,
* pracownika:
    * dodawanie nowego sprzętu do bazy,
    * dodawanie nowych klientów do bazy,
    * wyszukiwanie klientów po słowach kluczowych,
    * rejestracja wypożyczeń (jeden lub więcej rowerów i sztuk sprzętu na użytkownika),
    * <s>rejestracja uszkodzeń sprzętu podczas wypożyczenia</s>,
    * <s>generowanie kodów zniżkowych</s>,
    * generowanie raportów za dany okres działalności (przychody),
* klienta:
    * przeglądanie dostępnych rowerów i sprzętu,
    * składanie prośby o rezerwację,
    * korzystanie z kodów zniżkowych przy składaniu rezerwacji,
    * wyszukiwanie sprzętu po słowach kluczowych, kategoriach.
    
    
## Implementacja
Nie planuję tworzenia graficznego interfejsu użytkownika, dostęp do programu będzie możliwy poprzez polecenia tekstowe. 

Projekt będzie zrealizowany w języku Java z pomocą bazy danych PostgreSQL.

## Wykorzystywane wzorce projektowe

1. `Builder` - klasy Bike, Customer — dodawanie opcjonalnych informacji.
1. `Strategy` - wybieranie odpowiedniego sposobu wyliczania należności za wynajem (czy rower + akcesoria, czy samo
akcesorium, czy obowiązuje zniżka, czy wypożyczenie w święto itd.)
1. `Factory` - połączenie z bazą danych (łatwe przejście pomiędzy np. bazą testową w H2 a produkcyjną w PostgreSQL,
 bazą zapasową w SQLite itp.)
1. `Singleton`- w implementacji wzorca Factory,
1. `Facade` - ograniczenie dostępnych instrukcji dla różnych grup użytkowników (pracownik całość, klient mniej),
1. `Chain of responsibility` - przy wysyłaniu powiadomień o zbliżającym się terminie zakończenia wypożyczenia, mail -> 
   telefon -> "ręczne powiadomienie", w zależności od podanych przez klienta informacji,
1. `Iterator` - własny iterator do przeglądania wszystkich dostępnych aktualnie rowerów,
1. `Adapter` - adaptacja "pretty printera" wypożyczeń do generatora raportów,
1. `Data Mapper` - mapowanie obiektów z bazy danych do swoich odpowiedników w aplikacji, zapewnianie podstawowych
   operacji bazodanowych,
1. `Decorator` - dodawanie stałej zniżki klienta do ceny obliczonej już przez odpowiedni kalkulator,
