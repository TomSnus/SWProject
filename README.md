# SWProject
Dozent: Prof. Dr. Jobst

---------------
Partner
---------------
Tankstelle (Petrol Station) - Florian Heid
Raffinerie (Oil Comapny) - Thomas Stelzer
Spedition (Forwarding Comapny) - Josef Ilg

----------------
Szenatio
----------------

B2B Geschäftsbeziehung zwischen Tankstelle (Florian Heid), Raffinerie (Thomas Stelzer) und Spedition (Josef Ilg).

Die Bestellungen von Treibstoff beginnt bei der Tankstelle. Durch @Schedule-Methode werden die Tanksäulen entleert. 
Wenn die Literanzahl unter einen bestimmten Wert fällt, wird automatisch eine Bestellung an die Raffinerie weitergeleitet.

In der Raffinerie wird die Anfrage der Tankstelle verarbeitet, der Preis der Bestellung berechnet und 
eine generierte Lieferung an die Spedition weitergeleitet.
Durch eine @Schedule-Methode wird bei der Spedition der aktuelle Status der Bestellung mitverfolgt und abgespeichert.

In der Spedition wird der Transport an Tanklastwagen delegiert. 
Der Transport wird durch eine @Asynchronous-Methode parallel zum Hauptprogramm gesteuert.
Final wird durch einen Serviceaufruf die Lieferung an die Tankstelle zugestellt und die Tanksäulen werden automatisch aufgetankt.

Jegliche .xhtml-Seiten dienen als administrative Oberflächen, werden aber für den Control flow einer 
Bestellung nicht zwangsmäßig benötigt, da der Ablauf automatisiert wurde.

