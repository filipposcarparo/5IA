# Prova flag PendingIntent

### Autore: Enrico Da Ronche

### Classe 5^IA

### Istituto C.Zuccante

Il progetto consiste in un'applicazione android che consente di testare l'effetto dei diversi flag della classe PendingIntent. Nello specifico � possibile scegliere il flag da testare tramite la selezione di un RadioButton collegato. Una volta inserito il tempo d'attesa � possibile far partire il counter (utilizzo di un AlarmManager) al termine del quale verr� inviato un messaggio all'oggetto MyBroadcastReceiver precedentemente creato. Sar� possibile verificare comportamenti diversi a seconda del flag selezionato per il PendingIntent utilizzato, in particolare:
- FLAG_CANCEL_CURRENT -> se il PendingIntent esiste gi�, questo verr� cancellato per poi crearne uno uguale;
- FLAG_IMMUTABLE -> il PendingIntent che viene creato non pu� essere modificato;
- FLAG_NO_CREATE -> il PendingIntent, se non esiste gi�, non viene creato;
- FLAG_ONE_SHOT -> il PendingIntent pu� essere utilizzato solo una volta;
- FLAG_UPDATE_CURRENT -> se il PendingIntent esiste gi� viene aggiornato in conformit� col nuovo Intent.
