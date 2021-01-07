(Important: Esborrar prèviament els fitxers de sortida del directori per a cada test)

Pràctica 1: Java


Fase 1:
	Programes a executar:
		CLIMain --> A l'Intellij, pestanya Run i seleccionar edit Configurations per introduir els arguments de la CLI
		MailServiceDemo --> Simplement, executar el programa. Alguns tests no necessiten cap paràmetre a intorduïr i per tant es faràn automàticament.
					Altres tests sí que necessiten introduir paràmetres per teclat en funció dels valors que ens interessin comprovar.
		TestRunner --> Programa a executar per comprovar algunes funcions del MailStore

Fase 2:
	Programes a executar:
		AutomaticMessageFiltersDemo --> Execucutar el programa i obtindrem els resultats del test per pantalla
		EncodingMessagesDemo --> Execucutar el programa i obtindrem els resultats del test per pantalla
Fase 3:
	Programes a executar:
		RedisMailStoreDemo -->

			1. ObrirPowerShell (admin)
			2. Escriure dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
			3. Escriure dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
			4. Escriure wsl --set-default-version 2
			5. Obrir WSL (Debian)
			6. Escriure in PowerShell wsl --list --verbose
			7. Escriure wsl --set-version <distribution name> <versionNumber> (Debian)(2)
			8. Escriure wsl --set-default-version 2
			9. Escriure: redis-server en la WSL
			10.Escriure: redis-cli (other WSL)

			11. Escriure a la redis CLI: KEYS* to get users
		  				     lrange "key" 0 -1 (element 0 fins a l'últim)

		MailStoreFactDemo --> Execucutar el programa i obtindrem els resultats del test per pantalla

Fase 4: 
	Programes a executar --> Cap, el testeig d'aquesta part s'ha inclòs amb el test de la part 1, a l'hora de crear un MailService


Pràctica 2: Scala

	Programes a executar:;
		ScalaApp --> Execucutar el programa i obtindrem els resultats del test per pantalla