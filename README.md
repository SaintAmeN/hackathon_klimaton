# hackathon_klimaton

## Treść repozytorium
Projekt jest częścią hakatonu klimatycznego. W ramach projektu realizowane było zadanie związane z poprawą bezpieczeństwa dzieci podróżujących do szkoły, oraz kształtowanie dobrych nawyków wśród grupy docelowej.

Zadanie zostało zrealizowane w formie mikroserwisowej, użyte technologie:
- Spring Boot,
- Spring Boot Cloud,
- ReactJs,
- MongoDB,
- Java

Do uruchomienia projektu konieczne jest dodanie aliasów DNS (plik hosts):
- 127.0.0.1 klimaton-mongo-service
- 127.0.0.1 klimaton-eureka-service
- 127.0.0.1 klimaton-gateway-service
- 127.0.0.1 klimaton-server-host

W ramach zadania w ramach czasowych wydarzenia udało się zaimplementować:
- usługę discovery,
- bramę (gateway) API aplikacji backendowej,
- logowanie użytkowników z modelem zdobytych nagród,
- Frontend (Aplikacja ReactJS), folder ui,

Brakujący komponent:
- projekt aplikacji Android'owej 
- projekt aplikacji iOS
