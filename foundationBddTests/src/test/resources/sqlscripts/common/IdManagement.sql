USE [IdManagement]
GO
DELETE FROM [dbo].[role_permission] where [role_id] not in ('1','2','3','4')
GO
DELETE FROM [dbo].[user_role] where user_id not in ('1','2')
GO
DELETE FROM [dbo].[role] where role_code not in ('Administrator','Bio4CService','ProcessManager','Operator')
GO
DELETE FROM [dbo].[user_store] where user_name not in ('Bio4cService','Bio4CAdmin')
GO
SET IDENTITY_INSERT [dbo].[user_store] ON
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000005CD85B828657A952AE1414CEB055181DC4E52E3137C1AB1135294F12FF4689F61C44FF8DBCDC8FFF657D2AAC41256B033D6CE474EBF94711B41DA4AE7FB45DD7A63198B079D5466367F796E05C5ECC69C093132661E8C00FD5CDFCB56F0A675D, N'testUser', NULL, N'testUser', N'0123456789', N'testUser', 0, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'true', 0x020000006E81E21FC0EEF58892BB0BE413C3D96DEF57CFADD444356C6531D9F8F353BCF061DD593F116CA7B864E286147E6B49D7F79475221B366CF07D74A88AA4CCE6D2D32395740A5580F20FF9FB9BF3F37590C5A95E7200ECBED35D3F19F66F5700E6, N'testUserEnabled', NULL, N'testUserEnabled', N'0123456789', N'testUserEnabled', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabled', NULL, N'testUserDisabled', N'0123456789', N'testUserDisabled', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', 0x02000000F429D6A8AA9A20761C2C277B3FEF1237A83DE6C884A3CE32A17C05505870DE03AEA632B5085259C1F87810CBB6BE8CFD965FC4FD34BD2F9E3429D24F39C6EEA066802ED1D984FBB650FF9E8AB6B5727E778816A2BB8943B88D64E073152471D9, N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'123456789', N'testUsrFirstLog', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (7, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToAssignRole', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToAssignRole', NULL, N'testUserToAssignRole', N'0123456789', N'testUserToAssignRole', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (8, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToEditFields', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToEditFields', NULL, N'testUserToEditFields', N'0123456789', N'testUserToEditFields', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (9, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToResetPwd', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToResetPwd', NULL, N'testUserToResetPwd', N'0123456789', N'testUserToResetPwd', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (10, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000006239F1AE3EBC2798784A976D39C70AD8859DD99D36BB48DB5E94CEE1ADF9636D0A9858B7379A5870C16E69DD29E08069CF990BDA4DAE66A0C678594635ACF9B80F92AA054EF82D25FB0A92E088E3573F5A0260E0E5AC4F5171FD9199238D4F83, N'testUsrAfterResetPwd', NULL, N'testUsrAfterResetPwd', N'0123456789', N'testUsrAfterResetPwd', 1, 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (11, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', 0x02000000FAE1EE7AFF29418405539FB5F2C3A401CE552D5C3748C2F1DA7092633987988AEFAC5496B045F29CE51EF3FF23932067971F6E151B69138E6D90184E56A82D6754C217157E44DA6A07C596A0047BCE1F0C49F33140D33F169D5093A6DF9233E7, N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'0123456789', N'testRoleWithoutPerms', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (12, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserPref', N'true', 0x02000000101A1BC2F0D3DAA8D4DD9746363380C42D3F7993E138D12E0FEDC4F80D01C51E8FD4F5A2E9BDCEC7417C28863B3CF4EE3342A76551C9DCB3CD03B49492BCC13BA2FA4CFD25F500D20F6B09AA23D12243D25CA00F6CB9F660A4A68FE4AB5C1510, N'testUserPref', NULL, N'testUserPref', N'0123456789', N'testUserPref', 0, 0, 1, CAST(N'2022-05-21T09:03:44.2790000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (13, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabledFilter', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabledFilter', NULL, N'testUserDisabledFilter', N'0123456789', N'testUserDisabledFilter', 1, 0, 0, NULL, 0)
GO
SET IDENTITY_INSERT [dbo].[user_store] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (5, N'testRoleToRemovePermission', N'testRoleToRemovePermission', N'testRoleToRemovePermission', NULL, N'testRoleToRemovePermission', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (6, N'testRoleToAssign', N'testRoleToAssign', N'testRoleToAssign', NULL, N'testRoleToAssign', N'User Created', 1)
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status]) VALUES (7, N'testRolePermission', N'testRolePermission', N'testRolePermission', NULL, N'testRolePermission', N'User Created', 1)
GO
SET IDENTITY_INSERT [dbo].[role] OFF
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (3, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (4, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (5, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (6, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (7, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (8, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (9, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (10, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (11, 6)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (12, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (13, 3)
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
DELETE FROM dbo.user_preferences where user_name='testUserPref';
GO
UPDATE [IdManagement].[dbo].[user_store] set [is_locked]=0 ,[unsuccessful_login_attempts]=0 where user_name in ('Bio4cService','Bio4CAdmin');
GO