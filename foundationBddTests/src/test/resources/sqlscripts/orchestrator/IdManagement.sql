USE [IdManagement]
GO
DELETE FROM dbo.user_preferences;
GO
INSERT [dbo].[user_preferences] ( [preferences], [user_name], [default_view_id], [user_id], [user_lang_rfc]) VALUES ( N'{"preferredHomePage":"DeviceManagement"}', N'Bio4CAdmin', NULL, N'2', N'en-US')
GO
INSERT [dbo].[user_preferences] ( [preferences], [user_name], [default_view_id], [user_id], [user_lang_rfc]) VALUES ( N'{"preferredHomePage":"DeviceManagement"}', N'testUserForI18N', NULL, N'18', N'en-US')
GO

