#restart backup to take in account no job planned
Stop-Service bio4c-backup-management
Start-Service bio4c-backup-management

Get-ChildItem "C:\BIO4C\Data\Export\BackupManagement\backup" -Recurse |
Where-Object {!$_.PSIsContainer -and $_.LastWriteTime -lt (Get-Date).AddDays(-2) } |
Remove-Item -Force -ErrorAction SilentlyContinue