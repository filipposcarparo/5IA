#### Francesco Forcellato 5IA Zuccante
# BroadcastFagsExample
***
In questo esempio viene mostrato il diverso funzionamento del broadcast attraverso l'utilizzo dei `RadioButton`.


Attraverso il progetto `AM003_Receiver`, è stato possibile implementare una serie di `RadioButton` con i quali selezionare il diverso tipo di flag da utilizzare nell'applicazione.


I `RadioButton` hanno i seguenti flag:
| Flag | Descrizione |
| --- | --- |
| FLAG_CANCEL_CURRENT | se esiste già un pending intent, allora non ne genera uno nuovo |
| FLAG_IMMUTABLE | il pending intent creato non può venir modificato |
| FLAG_NO_CREATE | se non esiste il pending intent, al posto che crearlo ritorna `null` |
| FLAG_ONE_SHOT | Il pending intent può venir usato solo una volta |
| FLAG_UPDATE_CURRENT | se esiste già il pending intent, lo mantiene ma rimpiazzando i dati extra con il nuovo Intent |
