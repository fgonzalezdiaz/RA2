# Activitat 06: Espera wait - Respostes Teoriques

## 1. Per que s'atura l'execució al cap d'un temps?
L'execució s'atura perque es produeix una situacio de bloqueig. Com que tenim 10 assistents i nomes 5 places, arriba un moment en que l'esdeveniment esta ple.

Quan aixo passa, els assistents que volen entrar es queden bloquejats en el wait(` esperant un lloc lliure.

## 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70% (ferReserva)-30% (cancel·lar)? I si foren al reves?
Si canviem la probabilitat a un **70% de reservar i 30% de cancel·lar**, el bloqueig passaria molt mes rapid.

En el cas contrari, si fos un **30% de reservar i un 70% de cancel·lar**, passaria just l'oposat. L'esdeveniment estaria casi sempre buit o amb molt poques places ocupades.

## 3. Perque creus que fa falta la llista i no valdria nomes amb una variable sencera de reserves?
Es necessari fer servir una estructura de dades com una List per mantenir la coherencia i la seguretat de les reserves. Si nomes utilitzessim una variable int per saber quantes places queden, tindriem un problema de control.