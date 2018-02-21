# Dice simulator
di **Farhan Latif Gazi** `5^IA`

`Il progetto Dice simulator` simula il lancio di un dado a n facce (idicate dal utente).
Per la rappresentazione delle "facce" del dado sono state utilizzate più istanse di un oggetto che estende la classe `Fragment`.
Per creare le nuove istanse viene utilizzato il design pattern `Buil factory`.

Il progetto contiene le attività:
```
- MainActivity
- DiceSimulator
```
la classe che estende il `Fragment`:
```
- DiceFragment
```
Per passare da una faccia del dado all'altra i comandi sono:
```
- Swipe up (rotazione verso alto)
- Swipe down (rotazione verso basso)
- Swipe left (rotazione verso sinistra)
- Swipe right (rotazione verso destra)
- Double tap (fine simulazione)
```
Sono state utilizzate degli oggetti `Objectanimator` per realizzare le animazioni dei fragment.

## Strumenti utilizzati per lo sviluppo del progetto

- `Android Studio`: per scrivere il codice. 
