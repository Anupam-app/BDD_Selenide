$FilePath = $PSScriptRoot.tostring() + "/offset-timezone.properties"
$timezoneDiff=([datetime]::Now-[datetime]::Now.ToUniversalTime()).TotalSeconds
$FileContent = "offset.timezone.total.seconds=" + $timezoneDiff

$UTF8Only = New-Object System.Text.UTF8Encoding($false)
[System.IO.File]::WriteAllLines($FilePath, @($FileContent), $UTF8Only)