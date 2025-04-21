# Funkcje skrótu (hash)

### 1. Przygotuj aplikację, która pozwoli na wygenerowanie wartości skrótu zapisanego szesnastkowo na podstawie tekstowego wejścia, zadanego przez użytkownika. Skorzystaj z bibliotek natywnie dostępnych w wybranym środowisku programistycznym. Uwzględnij funkcje skrótu MD5, SHA-1, wszystkie warianty SHA-2 oraz SHA-3.
    
   Obsługa funkcji skrótu znajduje się w pliku [Main](Main.java)

### 2. Porównaj szybkość działania poszczególnych funkcji oraz długość ciągów wyjściowych. Użyj zbioru danych wejściowych o zróżnicowanej długości.

   Wyniki dla hashowania wiadomości "Hello World!":

   | **Funkcja** | **Ilość znaków** |        **Czas (ns)**         |
   |:-----------:|:----------------:|:----------------------------:|
   |     MD5     |        32        |  7988,831 ± 127,973  ns/op   |
   |    SHA-1    |        40        |  9499,727 ±  37,601  ns/op   |
   |   SHA-224   |        56        | 13725,331 ±   219,967  ns/op |
   |   SHA-256   |        64        |  17744,901 ± 258,698  ns/op  |
   |   SHA-384   |        96        | 27510,007 ± 3332,814  ns/op  |
   |   SHA-512   |       128        | 30586,664 ±   532,484  ns/op |
   | SHA-512/224 |        56        | 13609,247 ±   130,838  ns/op |
   | SHA-512/256 |        64        | 16454,800 ±   138,862  ns/op |
   |  SHA3-224   |        56        | 15017,980 ±   207,229  ns/op |
   |  SHA3-256   |        64        | 18224,168 ±   870,527  ns/op |
   |  SHA3-384   |        96        | 25884,786 ±  1447,620  ns/op |
   |  SHA3-512   |       128        | 41180,634 ± 15270,135  ns/op |

   Powstałe ciągi znaków dla wiadomości "Hello World!":

   ```
      MD5          : ed076287532e86365e841e92bfc50d8c
      SHA-1        : 2ef7bde608ce5404e97d5f042f95f89f1c232871
      SHA-224      : 4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b
      SHA-256      : 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
      SHA-384      : bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a
      SHA-512      : 861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8
      SHA-512/224  : f371319eee6b39b058ec262d4e723a26710e46761301c8b54c56fa722267581a
      SHA3-224     : 716596afadfa17cd1cb35133829a02b03e4eed398ce029ce78a2161d
      SHA3-256     : d0e47486bbf4c16acac26f8b653592973c1362909f90262877089f9c8a4536af
      SHA3-384     : f324cbd421326a2abaedf6f395d1a51e189d4a71c755f531289e519f079b224664961e385afcc37da348bd859f34fd1c
      SHA3-512     : 32400b5e89822de254e8d5d94252c52bdcb27a3562ca593e980364d9848b8041b98eabe16c1a6797484941d2376864a1b0e248b0f7af8b1555a778c336a5bf48
   ```

   Wyniki dla hashowania wiadomości Lorem100:
   
   | **Funkcja** | **Ilość znaków** |        **Czas (ns)**         |
   |:-----------:|:----------------:|:----------------------------:|
   |     MD5     |        32        | 9783,872 ±   157,788  ns/op  |
   |    SHA-1    |        40        | 11018,111 ±  699,964  ns/op  |
   |   SHA-224   |        56        | 15082,798 ±  3848,182  ns/op |
   |   SHA-256   |        64        | 16700,877 ±  3332,223  ns/op |
   |   SHA-384   |        96        | 25973,673 ±   285,811  ns/op |
   |   SHA-512   |       128        | 32587,728 ±   328,029  ns/op |
   | SHA-512/224 |        56        | 18090,603 ±   246,513  ns/op |
   | SHA-512/256 |        64        | 17954,568 ±   317,539  ns/op |
   |  SHA3-224   |        56        | 21269,212 ±   184,617  ns/op |
   |  SHA3-256   |        64        | 22495,650 ±  2774,016  ns/op |
   |  SHA3-384   |        96        | 33306,211 ±  1248,769  ns/op |
   |  SHA3-512   |       128        | 49332,271 ± 11358,554  ns/op |

### 3. Wygeneruj skrót dla słowa wejściowego, nie dłuższego niż 4 znaki. Skopiuj wartość uzyskaną dla funkcji MD5 i sprawdź, czy wartość wejściowa jest powszechnie znana.

   Słowo wejściowe: `Ena` \
   Wynik: `b7a16f02562d5c049a827d7cbdf4f704`

### Co można powiedzieć o bezpieczeństwie skrótów z krótkich haseł składowanych w bazach danych?

   Programy dekryptujące są w stanie szybko uzyskać wartość wejściową.

### 4. Na podstawie powszechnie dostępnych źródeł odpowiedz na pytanie – czy funkcję MD5 można uznać za bezpieczną? Czy dotychczas zostały znalezione dla niej jakiekolwiek kolizje?

   Funkcja MD5 nie jest bezpieczna. Jest uznawana za kryptograficznie złamaną funkcję hashującą. Kolizje zostały już znalezione w 2004 przez chińskich naukowców. Można stworzyć z różnych wiadomości M taki sam skrót H(M)

### 5. Dla wybranej przez siebie funkcji skrótu, zbadaj kolizje na pierwszych 12 bitach skrótu.

   Wybrana funkcja skrótu: 

### 6. Losowość wyjścia funkcji skrótu (kryterium SAC – Strict Avalanche Criteria) – przy zmianie pojedynczego bitu na wejściu, wszystkie bity wyjściowe powinny zmienić się z prawdopodobieństwem 0,5 każdy. Dla wybranej funkcji skrótu zbadaj tę własność.