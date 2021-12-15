NET USER "testUserEnabled" /DELETE
NET USER "testUserEnabled" "EN9Ed5&wP}" /ADD

NET USER "testUserDisabled" /DELETE
NET USER "testUserDisabled" "qI50x#J^n*" /ADD

NET USER "testUser" /DELETE
NET USER "testUser" "M)^40kMb8^" /ADD

NET USER "testUserToAssignRole" /DELETE
NET USER "testUserToAssignRole" "M)^40kMb8^" /ADD

NET USER "testUsrFirstLog" /DELETE
NET USER "testUsrFirstLog" "M)^40kMb8^" /ADD
NET localgroup administrators "testUsrFirstLog" /ADD

NET USER "testUserToEditFields" /DELETE
NET USER "testUserToEditFields" "M)^40kMb8^" /ADD

NET USER "testUserToResetPwd" /DELETE
NET USER "testUserToResetPwd" "M)^40kMb8^" /ADD

NET USER "testUsrAfterResetPwd" /DELETE
NET USER "testUsrAfterResetPwd" "Hmah2#h(7T" /ADD