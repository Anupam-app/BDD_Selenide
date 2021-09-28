$Server = $args[0]
$localScriptRoot = $args[1]
$scripts = Get-ChildItem $localScriptRoot | Where-Object {$_.Extension -eq ".sql"}
  
foreach ($s in $scripts)
{
	Write-Host "Running Script : " $s.Name -BackgroundColor DarkGreen -ForegroundColor White
	$script = $s.FullName
	Invoke-Sqlcmd -ServerInstance $Server -InputFile $script
}