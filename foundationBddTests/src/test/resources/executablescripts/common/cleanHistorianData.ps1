Get-ChildItem "C:\Historian\Data\Circular" -Recurse | 
Where-Object {!$_.PSIsContainer -and $_.LastWriteTime -lt (Get-Date).AddDays(-2) } | 
Remove-Item -Force -ErrorAction SilentlyContinue