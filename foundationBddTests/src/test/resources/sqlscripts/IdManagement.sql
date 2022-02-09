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
