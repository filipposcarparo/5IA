# Pagotto Emanuele

## Risponditore
In questo progetto si mira a simulare l'interazione con il cassiere di un negozio di videogiochi, che ci chiederà cosa vorremo comprare.

La determinazione delle frasi da dire al client è fatta tramite un automa a stati finiti. >Risponditore.java
L'automa è rappresentato da una matrice di adiacenza. >Node[][] 
Ogni nodo è identificato dal suo id univoco,che corrisponde al suo indice sull'asse y della matrice di adiacenza, e gli aggiornamenti avvengono in base alle risposte del client.

Vengono inoltre salvate le cose acquistate nel carrello, e tenuto traccia del valore dell'ordine.

Per facilitare l'accesso ai dati da parte del risponditore è stato creato un Node current, indicante l'attuale nodo in cui si trova l'automa.

## Chat TCP
Questo progetto mira a realizzare un servizio di chat tra due client.
Ogni client può selezionare con chi vuole conversare.

L'identificazione del client avviene attraverso una funzione di login, e al login vengono mandate al client tutte le conversazioni che lo vedevano come destinatario o mittente.

Sul client, attraverso una interfaccia grafica `che non funziona :(` è possibile scegliere con chi parlare, scegliendo tra gli utenti disponibili online presentati in una JComboBox. I messaggi vengono invece caricati ogniqualvolta che lo stato della combo box cambi, presentando su un pannello i messaggi scambiati tra le due persone, allineando a destra i messaggi mandati dall'utente, e a sinistra quelli dell'altro utente.

## Chatroom UDP
Con questo progetto, a differenza di quanto svolto nella chat TCP, si mira a creare una chatroom, in cui tutti gli utenti connessi possono contemporaneamente mandare e ricevere messaggi.

L'identificazione del client avviene attraverso una funzione di login, e al login vengono mandate al client tutte le conversazioni della chatroom, con anche indicato il nome del mittente.

Sul client, attraverso un'interfaccia grafica `che stavolta funziona :)` è possibile inserire i propri dati per il login o per registrarsi alla chatroom.

## Tris
Questo progetto ricrea il gioco del tris, con modalità giocatore contro giocatore e giocatore contro macchina.

Sono presenti due activity:
- `MainActivity`
- `Game`

L'activity Game si comporta in modo differente in base agli input in MainActivity (scelta di giocatore contro giocatore O giocatore contro macchina).

Sono presenti altre classi:
- `Board`     rappresentazione della griglia di gioco
- `Player`    rappresentazione del giocatore, sia umano che macchina
- `CellState` Enum per stabilire il contenuto di una cella della griglia di gioco

La classe `Board` è un oggetto Observable, osservato dalla activity Game.

## FlatDice
Questo progetto simula il lancio di un dado, con estrazione casuale, ogni faccia è rappresentata con un Fragment.

Nel progetto è presente solo una activity:
- `MainActivity` Contiene il contenitore per i Fragment generati, gestisce le gestures (swipe up/down/left/right, doppio tap) per la navigazione

Sono inoltre presenti due diversi Fragment
- `DiceFace`      Fragment che contiene la faccia estratta, ad ogni swipe corrisponde un nuovo DiceFace posizionato nella MainActivity
- `StartFragment` Fragment iniziale che da una breve spiegazione su cosa fare, e a cui si può ritornare con un doppio tap



