# Consegna Broadcast
La consegna è funzionante , è stato modificato il progetto AM003 e implemeta tutti e 5 i pendingIntent :
Una volta provato e letto la documentazione ne consegue che : 
### FLAG_CANCEL_CURRENT
Rileva che il pendingIntent esiste gia, lo elimina e lo sostituisce con uno nuovo.

### FLAG_IMMUTABLE
Rende immutabile in futuro il flag.

### FLAG_NO_CREATE
Similmente al CANCELL_CURRENT rileva se il PendingIntent è già esistente, se non è esistente restituisce null e NON ne crea uno nuovo.

### FLAG_ONE_SHOT
Indica che questo PendingIntent può essere utilizzato una sola volta.

### FLAG_UPDATE_CURRENT
Se il PendingIntent esiste, ne modifica i aggiornandoli con i dati nuovi.
