Execute Redis in WSL

1. Open PowerShell (admin)
2. Write dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
3. Write dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
4. Write wsl --set-default-version 2
5. Open WSL (Debian)
6. Write in PowerShell wsl --list --verbose
7. Write wsl --set-version <distribution name> <versionNumber> (Debian)(2)
8. Write wsl --set-default-version 2

9.In WSL Write: redis-server in WSL
		redis-cli (other WSL)

10. In redis cli: KEYS* to get users
		  lrange "key==user" 0 -1 (element 0 to last one)