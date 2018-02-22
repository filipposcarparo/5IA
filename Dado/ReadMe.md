#### Matteo Mognato 5IA 
# Dado Flat 
L'applicazione simula il funzionamento del lancio di un dado (con la caratteristica che non esce mai lo stesso numero consecutivamente). 
Il programma è basato sull'utilizzo di Fragment diversi che vengono cambiati ogni qual volta si esegue uno Swipe con animazioni differenti in base alla direzione dello swipe.
All'avvio dell'applicazione è presente il Fragment_Start che viene poi sostituito al primo swipe da Fragment_Faccia di cui viene creata un'instanza utilizzando un metodo factory. A seconda del valore con cui viene chiamato il Fragment_Faccia e quindi del numero che deve essere rappresentato nella faccia del dado viene scelta l'immagine relativa da impostare nell'ImageView della faccia del dado.
