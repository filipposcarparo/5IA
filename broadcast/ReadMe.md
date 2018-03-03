#### Matteo Mognato 5IA 3/3/2018
# Broadcast (Approfondimento uso dei Flag)
L'applicazione permette di simulare il funzionamento dei vari flag. E' possibile selezionare un flag alla volta attraverso l'utilizzo di RadioButton e un intero N da inserire in una TextView Per selezionare i secondi da aspettare prima di eseguire la vibrazione.
I vari flag che si può testare sono:
 - FLAG_CANCEL_CURRENT
   Flag che indica che se il PendingIntent descritto esiste già, quello corrente dovrebbe essere cancellato prima di generarne uno nuovo.
 - FLAG_IMMUTABLE
   Flag che indica che il PendingIntent creato deve essere immutabile.
 - FLAG_NO_CREATE
   Flag che indica che se il PendingIntent descritto non esiste già, allora restituisce semplicemente null invece di crearlo.
 - FLAG_ONE_SHOT
   Flag che indica che questo PendingIntent può essere utilizzato solo una volta.
 - 	FLAG_UPDATE_CURRENT
   Contrassegna che indica che se il PendingIntent descritto esiste già, mantienilo ma sostituisci i suoi dati extra con quelli del nuovo Intent.
