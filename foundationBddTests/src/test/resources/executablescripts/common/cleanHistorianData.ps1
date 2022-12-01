Get-ChildItem "C:\Historian\Data\Circular" -Recurse | 
Where-Object {$_.LastWriteTime -lt (Get-Date).AddDays(-2) } |
Remove-Item -Recurse -Force -ErrorAction SilentlyContinue