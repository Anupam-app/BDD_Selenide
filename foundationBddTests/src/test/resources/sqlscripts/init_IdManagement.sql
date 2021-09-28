USE [IdManagement]
GO
DELETE FROM [dbo].[user_role]
GO
DELETE FROM [dbo].[user_store]
GO
SET IDENTITY_INSERT [dbo].[user_store] ON 
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (1, N'Sun Nov 10 01:00:00 CET 1991', N'BIOP', N'xyz@merckgroup.com', N'X111111', N'true', 0x02000000456C829974D5A6F665A52BA374790170F10712D287F9DD43D681F349C4FABE7E17AEC023CF5DFAAAC3B8295729DF1CDDC98A59B0149680E86EB2F482EA90143B8F4BABB89D7098A12C08778C4ADC3621, N'First', N'male', N'Last', N'1234567890', N'Bio4cService', 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (2, N'Sun Nov 10 01:00:00 CET 1991', N'BIOP', N'xyz@merckgroup.com', N'X111112', N'true', 0x020000007DE63A77EE363A53A41E196D11A9F348C86BD696D5CF2F6AF15D27C9CEE464E838D5A9D9D6422D8E42CE2436CE07FCCD6DC5478DF69A94B0FDDC456BE1055AD37897E796E793D9E999126993D3DEE15B, N'First', N'male', N'Last', N'1234567890', N'Bio4CAdmin', 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (3, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'jYEkjhbrVh', N'true', 0x020000005CD85B828657A952AE1414CEB055181DC4E52E3137C1AB1135294F12FF4689F61C44FF8DBCDC8FFF657D2AAC41256B033D6CE474EBF94711B41DA4AE7FB45DD7A63198B079D5466367F796E05C5ECC69C093132661E8C00FD5CDFCB56F0A675D, N'testUser', NULL, N'testUser', N'', N'testUser', 0)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (4, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserEnabled', N'true', 0x020000006E81E21FC0EEF58892BB0BE413C3D96DEF57CFADD444356C6531D9F8F353BCF061DD593F116CA7B864E286147E6B49D7F79475221B366CF07D74A88AA4CCE6D2D32395740A5580F20FF9FB9BF3F37590C5A95E7200ECBED35D3F19F66F5700E6, N'testUserEnabled', NULL, N'testUserEnabled', N'', N'testUserEnabled', 1)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (5, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUserDisabled', N'false', 0x02000000B45512DBEC40C25B48F71766C234E0611C1F6B0EF06A0FF48C3A959F0AB51F6E7079B1D11E39AE3C2F688250AC1B5FF69D78919FA48CBE58CB5226CC822ED683D5667987ACA65BDC6E92AB3C4128205FABEC9E5CF2075E4F1AF9B53453BB9228, N'testUserDisabled', NULL, N'testUserDisabled', N'', N'testUserDisabled', 1)
GO
INSERT [dbo].[user_store] ([id], [bdate], [dept], [email], [employee_id], [enable], [encrypt_password], [first_name], [gender], [last_name], [phoneno], [user_name], [is_temporary_password]) VALUES (6, NULL, N'', N'alexis.thiebaut@merckgroup.com', N'testUsrFirstLog', N'true', 0x0200000028C7DEBA90CF923541E29FCF59F03D4DF297E7CF72DDAD24F1FFF0926BD2C6989371CADAE89587863949DF5E2A7D6FB1FCBC413EE4AE8EB7F678398A0CC10841AAAB5F6AF4BD265A460B93113B2951FFE7835A592C57A2583DF4A088B5FC59D6, N'testUsrFirstLog', NULL, N'testUsrFirstLog', N'', N'testUsrFirstLog', 1)
GO
SET IDENTITY_INSERT [dbo].[user_store] OFF
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
