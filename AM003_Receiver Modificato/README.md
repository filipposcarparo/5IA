# AM003_Receiver Modificato

Modifica del progetto in Android Studio del professore. Sono stati aggiunti 5 RadioButton che permettono di scegliere quale tipo di flag utilizzare.

## FLAG_CANCEL_CURRENT

Flag che indica che se esiste un PendigIntent, esso verrà eliminato e sostituito con uno nuovo.

## FLAG_IMMUTABLE

Controlla che il PendigIntent corrente sia immutabile. Ciò significa che gli argomenti che verranno passati al metodo di invio, utilizzati per riempire i campi mancanti del PendigIntent corrente, verranno ignorati.

## FLAG_NO_CREATE

Restituisce "null" nel caso non esista un PendigIntent, senza andare a crearne un nuovo.

## FLAG_ONE_SHOT

Indica che il PendigIntent corrente può essere utilizzato una sola volta. Nel caso venga utilizzato questo flag, dopo aver richiamato il metodo send(), il PendigIntent verrà eliminato.

## FLAG_UPDATE_CURRENT

Nel caso il PendigIntent esista già, quest'ultimo verrà mantenuto, ma i suoi dati verranno sostituiti con i dati del nuovo PendigIntent.
