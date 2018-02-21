Flat Dice

Questo progetto si basa sulla modifica del progett AM012-Fragment.

* Composizione
	- MainActivity
	- Dado
	- Fragment1

La prima è l'unica activity che gestisce il tocco sullo schermo attraverso l'interfaccia GestureDetector di Android. Per riconoscere da che parte a che parte dello schermo è effettuato lo swipe, viene utilizzato l'angolo attraverso una funzione matematica. Inizialmente era stato pensato per poter riconoscere anche le gesture verso gli angoli, che non sono state implementate per dar spazio al riconoscimento vocale. Infatti pronunciando determinate parole, è possibile cambiare la faccia del dado (è stato utilizzato lo speechrecognition di Android).
Gli altri due file sono dei Fragment, rispettivamente la schermata iniziale, il seconso, mentre il fragment che mostra le facce del dado, il primo. Viene utilizzato il metodo factory per creare una nuova istanza del fragment dado a cui viene passato un numero random per la generazione della faccia.