$user = $args[0]
$password = $args[1]
$CipHostname = $args[2]
$localScriptRoot = $args[3]
$scripts = Get-ChildItem $localScriptRoot | Where-Object {$_.Extension -eq ".ps1"}

$SecurePassword = $password | ConvertTo-SecureString -AsPlainText -Force
$cred = New-Object System.Management.Automation.PSCredential -ArgumentList $user, $SecurePassword
$SessionCip = New-PSSession -ComputerName $CipHostname -Credential $cred
  
foreach ($s in $scripts)
{
	Write-Host "Running Script : " $s.Name -BackgroundColor DarkGreen -ForegroundColor White
	$script = $s.FullName
	powershell Invoke-Command -Session $SessionCip -FilePath $script
}