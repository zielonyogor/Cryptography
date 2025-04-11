# Funkcje skrótu (hash)

### 1. Przygotuj aplikację, która pozwoli na wygenerowanie wartości skrótu zapisanego szesnastkowo na podstawie tekstowego wejścia, zadanego przez użytkownika. Skorzystaj z bibliotek natywnie dostępnych w wybranym środowisku programistycznym. Uwzględnij funkcje skrótu MD5, SHA-1, wszystkie warianty SHA-2 oraz SHA-3.
    
   Obsługa funkcji skrótu znajduje się w pliku [Main](Main.java)

### 2. Porównaj szybkość działania poszczególnych funkcji oraz długość ciągów wyjściowych. Użyj zbioru danych wejściowych o zróżnicowanej długości. 

   Wyniki dla hashowania wiadomości "Hello World!":

   | **Funkcja** | **Ilość znaków** | **Czas (ns)** |
   |:-----------:|------------------|---------------|
   |     MD5     | 32               | 25039300      |
   |    SHA-1    | 40               | 409700        |
   |   SHA-256   | 64               | 272800        |
   |   SHA-384   | 96               | 415700        |

   Powstałe ciągi znaków:

   ```
      MD5       : ed076287532e86365e841e92bfc50d8c
      SHA-1     : 2ef7bde608ce5404e97d5f042f95f89f1c232871
      SHA-256   : 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
      SHA-384   : bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a
   ```
      

### 3. Wygeneruj skrót dla słowa wejściowego, nie dłuższego niż 4 znaki. Skopiuj wartość uzyskaną dla funkcji MD5 i sprawdź, czy wartość wejściowa jest powszechnie znana. Co można powiedzieć o bezpieczeństwie skrótów z krótkich haseł składowanych w bazach danych?

### 4. Na podstawie powszechnie dostępnych źródeł odpowiedz na pytanie – czy funkcję MD5 można uznać za bezpieczną? Czy dotychczas zostały znalezione dla niej jakiekolwiek kolizje?

### 5. Dla wybranej przez siebie funkcji skrótu, zbadaj kolizje na pierwszych 12 bitach skrótu.

### 6. Losowość wyjścia funkcji skrótu (kryterium SAC – Strict Avalanche Criteria) – przy zmianie pojedynczego bitu na wejściu, wszystkie bity wyjściowe powinny zmienić się z prawdopodobieństwem 0,5 każdy. Dla wybranej funkcji skrótu zbadaj tę własność.