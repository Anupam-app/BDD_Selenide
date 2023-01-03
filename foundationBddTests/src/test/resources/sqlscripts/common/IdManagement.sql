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
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'faVppoqqKR', N'true', N'testUser', NULL, N'testUser', N'0123456789', N'testUser', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'false', N'testUserEnabled', NULL, N'testUserEnabled', N'0123456789', N'testUserEnabled', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'true', N'testUserDisabled', NULL, N'testUserDisabled', N'0123456789', N'testUserDisabled', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'', N'testUsrFirstLog', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (7, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToAssignRole', N'true', N'testUserToAssignRole', NULL, N'testUserToAssignRole', N'0123456789', N'testUserToAssignRole', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (8, NULL, N'CBChUvBDBH', N'alexis.thiebaut@merckgroup.com', N'jrscbSYXAv', N'true', N'testUserToEditFields', NULL, N'testUserToEditFields', N'0123456789', N'testUserToEditFields', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (9, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserToResetPwd', N'true', N'testUserToResetPwd', NULL, N'testUserToResetPwd', N'0123456789', N'testUserToResetPwd', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (10, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', N'testUsrAfterResetPwd', NULL, N'testUsrAfterResetPwd', N'0123456789', N'testUsrAfterResetPwd', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (11, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testRoleWithoutPerms', N'true', N'testRoleWithoutPerms', NULL, N'testRoleWithoutPerms', N'0123456789', N'testRoleWithoutPerms', 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (12, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserPref', N'true', N'testUserPref', NULL, N'testUserPref', N'0123456789', N'testUserPref', 0, 0, CAST(N'2022-05-21T09:03:44.2790000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (13, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabledFilter', N'false', N'testUserDisabledFilter', NULL, N'testUserDisabledFilter', N'0123456789', N'testUserDisabledFilter', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (14, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'UserDisabled', N'false', N'UserDisabled', NULL, N'UserDisabled', N'0123456789', N'UserDisabled', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (15, NULL, N'', N'varun.mittal@external.merckgroup.com', N'Acclock', N'true', N'Acclock', NULL, N'Acclock', N'0123456789', N'Acclock', 0, 0, CAST(N'2022-10-15T08:48:14.2200000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (16, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'Accunlock', N'true', N'Accunlock', NULL, N'Accunlock', N'0123456789', N'Accunlock', 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (17, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'reportUnauthUser', N'true', N'reportUnauthUser', NULL, N'reportUnauthUser', N'', N'reportUnauthUser', 0, 0, CAST(N'2022-05-21T07:42:58.3550000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (19, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserForI18N', N'true', N'testUserForI18N', NULL, N'testUserForI18N', N'', N'testUserForI18N', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (24, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'crzSXCuuCu', N'true', N'PENMCEhrEi', NULL, N'fIqklJQHtS', N'0123456789', N'RUJLXOVYAz', 0, 0, NULL, 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (18, NULL, N'Auto', N'alexis.thiebaut@merckgroup.com', N'123456', N'true', N'NewUserRole', NULL, N'NewUserRole', N'0987654321', N'NewUserRole', 0, 0, CAST(N'2022-10-19T13:03:34.5140000' AS DateTime2), 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [first_name], [gender], [last_name], [phoneno], [user_name], [is_locked], [unsuccessful_login_attempts], [last_failed_login_attempt], [is_catalog_user]) VALUES (25, NULL, N'', N'srikanth.gajjela@external.merckgroup.com', N'123456', N'true', N'changePwd', NULL, N'Tester', N'0123456789', N'testChangePwd', 0, 0, CAST(N'2022-05-22T07:42:58.3550000' AS DateTime2), 0)
GO
SET IDENTITY_INSERT [dbo].[user_store] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (5, N'testRoleToRemovePermission', N'testRoleToRemovePermission', N'testRoleToRemovePermission', NULL, N'testRoleToRemovePermission', N'User Created', 1, 0, N'ENABLED')
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (6, N'testRoleToAssign', N'testRoleToAssign', N'testRoleToAssign', NULL, N'testRoleToAssign', N'User Created', 1, 0, N'ENABLED')
GO
INSERT [dbo].[role] ([id], [description], [role_code], [role_name], [ccp_role_name], [windows_role_name], [role_type], [status], [internal_use_only], [role_status]) VALUES (7, N'testRolePermission', N'testRolePermission', N'testRolePermission', NULL, N'testRolePermission', N'User Created', 1, 0, N'ENABLED')
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
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (19, 3)
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
INSERT [dbo].[role_permission] ([role_id], [permission_id]) VALUES (8, 69)
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
UPDATE [IdManagement].[dbo].[user_store] set [is_locked]=0 ,[unsuccessful_login_attempts]=0 where user_name in ('Bio4cService','Bio4CAdmin');
GO