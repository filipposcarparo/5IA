# BroadcastReceiver con Flag

***Manuel Vivian***


Viene utilizzato l'esempio **AM003_Receiver** per implementare una sequenza di RadioButton, con il quale si selezionano diversi tipi di flag.

*Si definisce così un esempio di diverso funzionamento del broadcast in funzione dei flag utilizzati.*

I flag utilizzati per i radio button sono i seguenti:

- `FLAG_CANCEL_CURRENT`	se il pending intent selezionato esiste già, allora non se ne crea un'altro;
- `FLAG_IMMUTABLE`	il pending intent creato non può subire modifiche di alcun genere;
- `FLAG_NO_CREATE`	se il pending intent selezionato non esiste, viene ritornato null (non se ne crea un'altro);
- `FLAG_ONE_SHOT`	Il pending intent selezionato può essere utilizzato un'unica volta;
- `FLAG_UPDATE_CURRENT`	se il pending intent selezionato esiste già, esso viene riutilizzato con il rimpiazzo dei dati provenienti da un nuovo intent.