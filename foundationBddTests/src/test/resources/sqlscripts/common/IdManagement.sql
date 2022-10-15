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
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (1, N'Sat Nov 09 18:00:00 CST 1991', N'BIOP', N'xyz@merckgroup.com', N'X111111', N'true', 0x020000002D2C581FA99D5166D00140B764D81CF769D9D7B54874D371BE7BFE8B95D594C93FF0BD66585444B9F333B8A3E3F83D0A7A290F9818AB1115AC242C97221FEDA0F36618DF65B4CC56C952BDC4DABDFE92FBCA1FBB8C682FFF29436272500CAAEE, N'ServiceExpert', N'male', N'Bio4C', N'1234567890', N'Bio4cService', 0, 0, 0, CAST(N'2022-10-05T08:12:42.1070000' AS DateTime2), 0, N'U2VjcmV0IFNhbHQ=', N'AAAAAAAAAAAAAAAAAAAAAA==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (2, N'Sat Nov 09 18:00:00 CST 1991', N'BIOP', N'xyz@merckgroup.com', N'X111112', N'true', 0x020000001632DDD039DD43557C97B597C2E042800B39D4C7D312D9D503B01D23D40CC5324DBF37581795A595EDBDABB203FDCDE50290B9F588EB551E861742719F2418E3E8501F8DB6E7199FEDD4E0642D7F4C648B8ABD68CCFAF490DA81106C72766219, N'Administrator', N'male', N'Bio4C', N'1234567890', N'Bio4CAdmin', 0, 0, 0, CAST(N'2022-10-05T08:32:18.9840000' AS DateTime2), 0, N'U2VjcmV0IFNhbHQ=', N'AAAAAAAAAAAAAAAAAAAAAA==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000005CD85B828657A952AE1414CEB055181DC4E52E3137C1AB1135294F12FF4689F61C44FF8DBCDC8FFF657D2AAC41256B033D6CE474EBF94711B41DA4AE7FB45DD7A63198B079D5466367F796E05C5ECC69C093132661E8C00FD5CDFCB56F0A675D, N'testUser', NULL, N'testUser', N'0123456789', N'testUser', 0, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'true', 0x020000006E81E21FC0EEF58892BB0BE413C3D96DEF57CFADD444356C6531D9F8F353BCF061DD593F116CA7B864E286147E6B49D7F79475221B366CF07D74A88AA4CCE6D2D32395740A5580F20FF9FB9BF3F37590C5A95E7200ECBED35D3F19F66F5700E6, N'testUserEnabled', NULL, N'testUserEnabled', N'0123456789', N'testUserEnabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabled', NULL, N'testUserDisabled', N'0123456789', N'testUserDisabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', 0x020000002C62DE0DEFB6F1DDA57ECFDF74E64352258CD42A2D731779D8D15C7380C680BA7178B8A64FD7A6D9F80A45D321C62F23B5578F69DA507455E1B7944D352CDEF9F1584462ED10B8669808ED3C01B1ACC2C4E974CCBAB4C2CB8CA4371D2A1A79EC, N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'123456789', N'testUsrFirstLog', 0, 0, 0, NULL, 0, N'IYF+y2XRQdHy4hjxZ1ofJw==', N'QckyykPRVfHNdoFBS+PotQ==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (7, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToAssignRole', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToAssignRole', NULL, N'testUserToAssignRole', N'0123456789', N'testUserToAssignRole', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (8, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToEditFields', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToEditFields', NULL, N'testUserToEditFields', N'0123456789', N'testUserToEditFields', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (9, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToResetPwd', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToResetPwd', NULL, N'testUserToResetPwd', N'0123456789', N'testUserToResetPwd', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (10, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x02000000C1A9DBC65775DC38D6E4AABE8B058EAE7D1CB2406B876E43132A629E6C0AEC7109E9C010FDD99B6C4E7626F48731E994006B1C9A2E2BA197DD9847159D767EFEDBB4079E166CC75BA837D94D78FFAED722E0BE29B495DF7A27A27F5D6C14BC26, N'testUsrAfterResetPwd', NULL, N'testUsrAfterResetPwd', N'0123456789', N'testUsrAfterResetPwd', 0, 0, 0, NULL, 0, N'82mDR7VRvTNtcmksoNYOpw==', N'tqRcZRcScmY2q2YkaoCOXQ==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (11, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', 0x02000000F82EA4BECFFCE7D1A7E0AE3BDBBE9F1DB3E4CC11777DF946DF4514CA96A3E15923CD635AE66697BB715B67A3735F57F2C0AAC4FE5C96B338084619189A0102899809AEC6183B1FC97C226984AE3E301AF89BA66734C5657D963B2CD5EC4E4750, N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'0123456789', N'testRoleWithoutPerms', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, N'rH8AGgOyobuPCu+ZdsAE/g==', N'L5cCPMz423z4B9PEJ06g7w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (12, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserPref', N'true', 0x020000003104992C152C099F38DC1175B17756F516D9FFD7912C102ECB049002D7BDAF7C29E3D9A609C37A531A50AA23275072EDD605EF166A3736C4A51B23E4A95A81FE7CE991ECB5FFF3F46B48692E3B6A34932FA3EB64DA952F8FA3BCA70F1155BE15, N'testUserPref', NULL, N'testUserPref', N'0123456789', N'testUserPref', 0, 0, 0, CAST(N'2022-05-21T09:03:44.2790000' AS DateTime2), 0, N'7GigNVQ+X1k8wADKqcvy0w==', N'i+i7+TDptdyT8an4h9Dg1w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (13, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabledFilter', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabledFilter', NULL, N'testUserDisabledFilter', N'0123456789', N'testUserDisabledFilter', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (14, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'UserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'UserDisabled', NULL, N'UserDisabled', N'0123456789', N'UserDisabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (15, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'Acclock', N'true', 0x0200000083229E060608175FBE16E520E788BEDDBD18D213C3940D0765943836ED86FB52CE5F52042397297D5FBD51E381043009529FEA262BC2E5AA019552F6E314DC93711BA17E2C8E332B5FD92690C2A51B7B59828C939383ED13FC8A5DFDC3CA768F, N'Acclock', NULL, N'Acclock', N'0123456789', N'Acclock', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, N'yXj45PDjkWBdjYYVLeB4sA==', N'lZOZI6fCshG+tb36+QfvYw==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (16, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'Accunlock', N'true', 0x020000007DCC245DD8BB35B36F5D354E0D6698BD9B5281177342C86E31364CF01C650FFA788730A23FD4B8D31AAA48C0A507CCB23F3A1CFBE7922699F538D8FDC08E88ACA440C5CED73CBE5C70A7BEF72ACE40E186DC2A6259CA2B43DCE35E148C713642, N'Accunlock', NULL, N'Accunlock', N'0123456789', N'Accunlock', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (17, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'reportUnauthUser', N'true', 0x0200000049705B416E753FA2951939ECB343B73329985476884F3A2924B80652C971ADA1C2A5F91898F4756282A1F7550DDB00AD7C8C254C09C2AA8CAA017106F35A9F3435849672305FEDE430575FCBC6ECFE8091C228FDE9EC7598FD6EC00352FA61A1, N'reportUnauthUser', NULL, N'reportUnauthUser', N'', N'reportUnauthUser', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, N'PaVODEMTTm1omCtpM/7lvg==', N'f8wlzx2ZwFcdqyUQ02Xm1w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (19, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserForI18N', N'true', 0x02000000507CBAB959087CF6B9726B7252A69773321505EF613F644A1E22AED98A9F57E375C66641D8B173D9A16BBE202ACEEE17945DEC08F5FFCB0EDA64B8069C02054F6BDE23F9613CA340A73D877A1246E522A33A3149DF42E78A84ECEF7377467C0A, N'testUserForI18N', NULL, N'testUserForI18N', N'', N'testUserForI18N', 0, 0, 0, NULL, 0, N'MCVc1t4F+WRi/t4Udui8ow==', N'2P0iN7zxGZua4AxJlZN2WA==')
GO
SET IDENTITY_INSERT [dbo].[user_store] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (5, N'testRoleToRemovePermission', N'testRoleToRemovePermission', N'testRoleToRemovePermission', NULL, N'testRoleToRemovePermission', N'user.role.roletype', 1, 0, N'ENABLED')
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (6, N'testRoleToAssign', N'testRoleToAssign', N'testRoleToAssign', NULL, N'testRoleToAssign', N'user.role.roletype', 1, 0, N'ENABLED')
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (7, N'testRolePermission', N'testRolePermission', N'testRolePermission', NULL, N'testRolePermission', N'user.role.roletype', 1, 0, N'ENABLED')
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (8, N'testroleviewpermission', N'testroleviewpermission', N'testroleviewpermission', NULL, N'testroleviewpermission', N'User Created', 1, 0, N'ENABLED')
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
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (14, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (15, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (16, 3)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (17, 8)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (19, 1)
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
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 83)
GO
DELETE FROM dbo.user_preferences;
GO
UPDATE [IdManagement].[dbo].[user_store] set [is_locked]=0 ,[unsuccessful_login_attempts]=0 where user_name in ('Bio4cService','Bio4CAdmin');
GO
