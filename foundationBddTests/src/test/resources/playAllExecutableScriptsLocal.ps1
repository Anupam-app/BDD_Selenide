$localScriptRoot = $args[0]
$scripts = Get-ChildItem $localScriptRoot | Where-Object {$_.Extension -eq ".ps1"}
  
foreach ($s in $scripts)
{
	Write-Host "Running Script : " $s.Name -BackgroundColor DarkGreen -ForegroundColor White
	$script = $s.FullName
	powershell -File $script
}