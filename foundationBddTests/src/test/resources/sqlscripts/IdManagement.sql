USE [IdManagement]
GO
DELETE FROM [dbo].[role_permission]
GO
DELETE FROM [dbo].[user_role]
GO
DELETE FROM [dbo].[role]
GO
DELETE FROM [dbo].[user_store]
GO
SET IDENTITY_INSERT [dbo].[user_store] ON
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (1, N'Sun Nov 10 01:00:00 CET 1991', N'BIOP', N'xyz@merckgroup.com', N'X111111', N'true', 0x02000000456C829974D5A6F665A52BA374790170F10712D287F9DD43D681F349C4FABE7E17AEC023CF5DFAAAC3B8295729DF1CDDC98A59B0149680E86EB2F482EA90143B8F4BABB89D7098A12C08778C4ADC3621, N'First', N'male', N'Last', N'1234567890', N'Bio4cService', 0, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (2, N'Sun Nov 10 01:00:00 CET 1991', N'BIOP', N'xyz@merckgroup.com', N'X111112', N'true', 0x020000007DE63A77EE363A53A41E196D11A9F348C86BD696D5CF2F6AF15D27C9CEE464E838D5A9D9D6422D8E42CE2436CE07FCCD6DC5478DF69A94B0FDDC456BE1055AD37897E796E793D9E999126993D3DEE15B, N'First', N'male', N'Last', N'1234567890', N'Bio4CAdmin', 0, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000005CD85B828657A952AE1414CEB055181DC4E52E3137C1AB1135294F12FF4689F61C44FF8DBCDC8FFF657D2AAC41256B033D6CE474EBF94711B41DA4AE7FB45DD7A63198B079D5466367F796E05C5ECC69C093132661E8C00FD5CDFCB56F0A675D, N'testUser', NULL, N'testUser', N'', N'testUser', 0, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'true', 0x020000006E81E21FC0EEF58892BB0BE413C3D96DEF57CFADD444356C6531D9F8F353BCF061DD593F116CA7B864E286147E6B49D7F79475221B366CF07D74A88AA4CCE6D2D32395740A5580F20FF9FB9BF3F37590C5A95E7200ECBED35D3F19F66F5700E6, N'testUserEnabled', NULL, N'testUserEnabled', N'', N'testUserEnabled', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabled', NULL, N'testUserDisabled', N'', N'testUserDisabled', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'', N'testUsrFirstLog', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (7, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToAssignRole', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToAssignRole', NULL, N'testUserToAssignRole', N'', N'testUserToAssignRole', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (8, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToEditFields', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToEditFields', NULL, N'testUserToEditFields', N'', N'testUserToEditFields', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (9, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToResetPwd', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToResetPwd', NULL, N'testUserToResetPwd', N'', N'testUserToResetPwd', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (10, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000009203AEC38A3E4B202C86A8A547C6B123F46C3717D0487589D35B7DEBDB3A85151CDFA2D5722B3405A1AFFE1CB81B446702E9C51D28C77A080FDFBE32A964B7DD607B167BC8DA98E349FAADBD7FDB5D943CE08687A6F9B45356AAD4F7BC20B84D, N'testUsrAfterResetPwd', NULL, N'testUsrAfterResetPwd', N'', N'testUsrAfterResetPwd', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (11, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', 0x0200000060FDE0EB0208D9A5B5C715C52443CB0D6E3E3AAFEB9362A021A0926E0F8645ADF3C494FAEC3E125E7CF3B9B5670A483A8EFBAB268C4BA5CBE67578C1F05CD2171C7DD5D3F14A670C265E91DECF2BF86686376BC0AB16FA3C424A730AF14F409A, N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'', N'testRoleWithoutPerms', 0, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (12, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', 0x0200000060FDE0EB0208D9A5B5C715C52443CB0D6E3E3AAFEB9362A021A0926E0F8645ADF3C494FAEC3E125E7CF3B9B5670A483A8EFBAB268C4BA5CBE67578C1F05CD2171C7DD5D3F14A670C265E91DECF2BF86686376BC0AB16FA3C424A730AF14F409A, N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'', N'testRoleWithoutPerms', 0, 0, 0, NULL, 0)
GO
SET IDENTITY_INSERT [dbo].[user_store] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (1, N'Administrator', N'Administrator', N'Administrator', N'SYSTEM ADMINISTRATOR', N'Bio4cAdminGroup', N'System Defined', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (2, N'Bio4CService', N'Bio4CService', N'Bio4CService', N'ENGINEERS', N'Bio4CServiceGroup', N'System Defined', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (3, N'ProcessManager', N'ProcessManager', N'ProcessManager', N'QA USERS', N'ProcessManagerGroup', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (4, N'Operator', N'Operator', N'Operator', N'OPERATORS', N'OperatorGroup', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (5, N'testRoleToRemovePermission', N'testRoleToRemovePermission', N'testRoleToRemovePermission', NULL, N'testRoleToRemovePermission', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (6, N'testRoleToAssign', N'testRoleToAssign', N'testRoleToAssign', NULL, N'testRoleToAssign', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (7, N'testRolePermission', N'testRolePermission', N'testRolePermission', NULL, N'testRolePermission', N'User Created', 1)
GO
SET IDENTITY_INSERT [dbo].[role] OFF
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (1, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (2, 1)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (3, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (4, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (5, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (6, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (7, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (8, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (9, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (10, 2)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (11, 6)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (12, 6)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 2)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 3)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 4)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 5)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 6)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 7)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 8)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 9)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 10)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 11)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 12)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 13)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 14)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 15)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 21)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 22)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 23)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 24)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 25)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 26)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 27)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 57)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 58)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 59)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 60)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 61)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 62)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 63)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 64)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 65)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 69)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 70)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 71)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 72)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 73)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 74)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 75)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 76)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 77)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 78)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 79)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 80)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 81)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 82)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 84)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 85)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 86)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 87)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 88)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 89)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 90)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 91)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 92)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 93)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 94)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 95)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 96)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 97)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 98)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 99)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 100)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 101)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 102)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 103)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 104)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 105)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 106)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 107)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 108)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 109)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 110)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 111)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 112)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 113)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 114)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 115)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 116)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 117)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 118)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 119)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 120)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 121)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 122)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 123)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 124)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 125)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 126)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 127)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 128)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 129)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 130)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 131)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 132)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 133)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (1, 134)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 2)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 3)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 4)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 5)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 6)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 7)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 8)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 9)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 10)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 11)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 12)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 13)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 14)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 15)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 21)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 22)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 23)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 24)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 25)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 26)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 27)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 57)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 58)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 59)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 60)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 61)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 62)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 63)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 64)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 65)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 69)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 70)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 71)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 72)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 73)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 74)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 75)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 76)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 77)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 78)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 79)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 80)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 81)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 82)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 84)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 85)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 86)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 87)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 88)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 89)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 90)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 91)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 92)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 93)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 94)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 95)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 96)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 97)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 98)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 99)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 100)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 101)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 102)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 103)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 104)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 105)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 106)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 107)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 108)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 109)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 110)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 111)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 112)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 113)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 114)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 115)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 116)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 117)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 118)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 119)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 120)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 121)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 122)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 123)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 124)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 125)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 126)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 127)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 128)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 129)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 130)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 131)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 132)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 133)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (2, 134)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 62)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 69)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 70)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 71)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 72)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 73)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 74)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 76)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 77)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 78)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 80)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 81)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 84)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 85)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 86)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 87)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 88)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 89)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 90)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 91)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 92)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 93)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 94)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 95)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 96)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 97)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 98)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 99)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 100)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 101)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 102)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 103)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 104)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 105)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 106)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 107)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 108)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 109)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 110)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 111)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 112)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 113)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 115)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 118)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 119)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 120)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 121)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 122)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 123)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 124)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 125)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 126)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 127)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 128)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 129)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 130)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 131)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 132)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (3, 133)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 62)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 69)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 70)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 71)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 76)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 77)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 78)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 80)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 84)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 86)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 87)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 88)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 98)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 102)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 103)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 106)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 110)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 115)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 119)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 122)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 123)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 124)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 125)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 126)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 127)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 130)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 132)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (4, 133)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (5, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (5, 3)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 3)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 56)
GO
