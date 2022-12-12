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
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'faVppoqqKR', N'true', 0x02000000113BFAD8804F35D57B6A294AE2768AA8A55EBE9BBD463A3FF274A9AFD9DE4815A2B01BFE1C1CAC48777DFE842CC56B5A98B4B3844F87C38227EF57EB2857B1C68176BBB1E0BC2651AFC4238940AD304446048199CF230EA3027FA6BCECE557DB, N'testUser', NULL, N'testUser', N'0123456789', N'testUser', 0, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'false', 0x02000000E9D4ED3D169931E010E233E105AD8ED66E974A7D8D0A75162BA1A4B67C4E94F19E624B1A6D0A77837D85D1C5244A0CCB8C0F78FFD00B8A414169911C6AB284179C1E71CC0B5BDC0D291BD9A722BC14012A7D9C6726EAD1E1E0FE9DCEF8A89E48, N'testUserEnabled', NULL, N'testUserEnabled', N'0123456789', N'testUserEnabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'true', 0x0200000081909D2BFBDBCB6A1ECDAD73AF56E26363E0A19DCBC2B4BC08C532D6307F56CA440EF0D1A39AC05CC62C5F484E4D706390A4C412B277DE1A43CF5E6134CD9BAC5805EFD379080D20BEA7BBE3A3FE1F239BAFA61E87B7FE6FE2840D8B575CA2B3, N'testUserDisabled', NULL, N'testUserDisabled', N'0123456789', N'testUserDisabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', 0x020000004B5E76FF87929309D06F8F60022A78416286680EB8303D66C9F73775A8C136D9E36150894B645ACD2D6DC293A56404AE61C6D801E1F495B40A92726F361AEF335ADF8B3784E074D0EC8464BD7861D3D45572BCA1D389CA4BBDA924D2D5DFE170, N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'', N'testUsrFirstLog', 1, 0, 0, NULL, 0, N'PMhVo+fPE6WtUJ0ppa7NxA==', N'B3vjRYbhp5o20V5U1XqdXQ==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (7, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToAssignRole', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToAssignRole', NULL, N'testUserToAssignRole', N'0123456789', N'testUserToAssignRole', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (8, NULL, N'CBChUvBDBH', N'alexis.thiebaut@merckgroup.com', N'jrscbSYXAv', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUserToEditFields', NULL, N'testUserToEditFields', N'0123456789', N'testUserToEditFields', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (9, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToResetPwd', N'true', 0x02000000DDB2AAA1760530F7722508CADB55ACB701CF428669CAF8EE76C2BF31B92A498E39C34C622DA2A0558C3E862B1280E1809D2F353C8D02460B8DB5B8D003E899A2862F76D8895C5CED6B048D49FFFFD4982D7FEF2BEA1F06456493B6D064397D9B, N'testUserToResetPwd', NULL, N'testUserToResetPwd', N'0123456789', N'testUserToResetPwd', 1, 0, 0, NULL, 0, N'7Iieeo2JXbAsZW5QQMdQ6w==', N'uFflzJ8fgBIJsuOxydALcQ==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (10, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x0200000047B1B5E69FFE206AC617DF4AAFF51E6E8C170CE6494133E233EEDDFBABDE8C457F483A33F21C08DD8697EC2AC18E86353BC330345E3584C4CAEA45E1153186FF2044EB235645C527D3BC5F99A5F265B443C8F15AF1981E7D15EA76E48860F180, N'testUsrAfterResetPwd', NULL, N'testUsrAfterResetPwd', N'0123456789', N'testUsrAfterResetPwd', 1, 0, 0, NULL, 0, N'zid283lDvJZXEYwkJ8gNbw==', N'FeaHlhFmeWMMs69LNrvD4Q==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (11, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', 0x02000000F82EA4BECFFCE7D1A7E0AE3BDBBE9F1DB3E4CC11777DF946DF4514CA96A3E15923CD635AE66697BB715B67A3735F57F2C0AAC4FE5C96B338084619189A0102899809AEC6183B1FC97C226984AE3E301AF89BA66734C5657D963B2CD5EC4E4750, N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'0123456789', N'testRoleWithoutPerms', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, N'rH8AGgOyobuPCu+ZdsAE/g==', N'L5cCPMz423z4B9PEJ06g7w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (12, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserPref', N'true', 0x020000003104992C152C099F38DC1175B17756F516D9FFD7912C102ECB049002D7BDAF7C29E3D9A609C37A531A50AA23275072EDD605EF166A3736C4A51B23E4A95A81FE7CE991ECB5FFF3F46B48692E3B6A34932FA3EB64DA952F8FA3BCA70F1155BE15, N'testUserPref', NULL, N'testUserPref', N'0123456789', N'testUserPref', 0, 0, 0, CAST(N'2022-05-21T09:03:44.2790000' AS DateTime2), 0, N'7GigNVQ+X1k8wADKqcvy0w==', N'i+i7+TDptdyT8an4h9Dg1w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (13, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabledFilter', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabledFilter', NULL, N'testUserDisabledFilter', N'0123456789', N'testUserDisabledFilter', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (14, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'UserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'UserDisabled', NULL, N'UserDisabled', N'0123456789', N'UserDisabled', 1, 0, 0, NULL, 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (15, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'Acclock', N'true', 0x020000006BC5BDA070521DD1154240D70269AA0FEA7F711EB0014798A3E59963C9CC5E02D459AA9EB3C9F5DBEEFC57FBAC7FBCC84C154ED32EA0848FFC4A191CCFE4F434A303701439D0BE47ECB9AF401FE774CBFFFBF3E7E40EAC1FD89829B552B3A596, N'Acclock', NULL, N'Acclock', N'0123456789', N'Acclock', 1, 0, 0, CAST(N'2022-10-15T08:48:14.2200000' AS DateTime2), 0, N'gYGTOx0asaTpU/XTDMEvwA==', N'ikLXJG2b7nOTlIMFuDKQlA==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (16, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'Accunlock', N'true', 0x020000007DCC245DD8BB35B36F5D354E0D6698BD9B5281177342C86E31364CF01C650FFA788730A23FD4B8D31AAA48C0A507CCB23F3A1CFBE7922699F538D8FDC08E88ACA440C5CED73CBE5C70A7BEF72ACE40E186DC2A6259CA2B43DCE35E148C713642, N'Accunlock', NULL, N'Accunlock', N'0123456789', N'Accunlock', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, NULL, NULL)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (17, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'reportUnauthUser', N'true', 0x0200000049705B416E753FA2951939ECB343B73329985476884F3A2924B80652C971ADA1C2A5F91898F4756282A1F7550DDB00AD7C8C254C09C2AA8CAA017106F35A9F3435849672305FEDE430575FCBC6ECFE8091C228FDE9EC7598FD6EC00352FA61A1, N'reportUnauthUser', NULL, N'reportUnauthUser', N'', N'reportUnauthUser', 0, 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0, N'PaVODEMTTm1omCtpM/7lvg==', N'f8wlzx2ZwFcdqyUQ02Xm1w==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (19, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserForI18N', N'true', 0x02000000507CBAB959087CF6B9726B7252A69773321505EF613F644A1E22AED98A9F57E375C66641D8B173D9A16BBE202ACEEE17945DEC08F5FFCB0EDA64B8069C02054F6BDE23F9613CA340A73D877A1246E522A33A3149DF42E78A84ECEF7377467C0A, N'testUserForI18N', NULL, N'testUserForI18N', N'', N'testUserForI18N', 0, 0, 0, NULL, 0, N'MCVc1t4F+WRi/t4Udui8ow==', N'2P0iN7zxGZua4AxJlZN2WA==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv]) VALUES (24, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'crzSXCuuCu', N'true', 0x020000001C635811CD2D5098884C5F67E76023CF2F2AE091A35842FE1951066723F21BC2EB9B17E53A9EA61856D4E90809863E34D336378230FA8D2B90CCBA540573DA3897883594A5D96C6766B9836AA39DCBF65B6C5F9457FA85E179CB52D0D7C20906, N'PENMCEhrEi', NULL, N'fIqklJQHtS', N'0123456789', N'RUJLXOVYAz', 1, 0, 0, NULL, 0, N'6B2mrQNwGpfQVCVz0WFF9g==', N'MesNzA9xENR8gVm/S5Bm7A==')
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user], [salt], [iv], [password_expiry_date]) VALUES (18, NULL, N'Auto', N'alexis.thiebaut@merckgroup.com', N'123456', N'true', 0x02000000626FB4C10ABF90AA4461FC84AB6BABB8B1FADB9869ED2395577373BC3F022E25D13AD6B5C5A4F2FE04E257C781E4CBD4EF23795815BC7793F7E9D5294B456C8DCDFD4F3C90003AB2B758478DAA5ABCAEDABDFCF3B1C36FE2F5B287960D8A4F5E, N'NewUserRole', NULL, N'NewUserRole', N'0987654321', N'NewUserRole', 0, 0, 0, CAST(N'2022-10-19T13:03:34.5140000' AS DateTime2), 0, N'a9GxMBSiBNT2JZbX5e3ZRA==', N'EIUcBkOiX+2toB0rxvNqAA==', NULL)
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
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (9, N'TestRole', N'TestRole', N'TestRole', N'TestRole', N'TestRole', N'User Created', 1, 0, N'ENABLED')
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
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (18, 9)
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (19, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (5, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (5, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 1)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 57)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (6, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 56)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 62)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 141)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 58)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 64)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 69)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 70)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 83)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 85)
GO
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (9, 117)
GO
DELETE FROM dbo.user_preferences;
GO
INSERT [dbo].[user_preferences] ( [preferences], [user_name], [default_view_id], [user_id], [user_lang_rfc]) VALUES ( N'{}', N'Bio4CAdmin', NULL, N'2', N'en-US')
GO
UPDATE [IdManagement].[dbo].[user_store] set [is_locked]=0 ,[unsuccessful_login_attempts]=0 where user_name in ('Bio4cService','Bio4CAdmin');
GO
