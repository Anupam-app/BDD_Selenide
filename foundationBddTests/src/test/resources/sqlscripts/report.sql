USE [ReportManagement]
GO
DELETE FROM [dbo].[runheader]
GO
SET IDENTITY_INSERT [dbo].[runheader] ON
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (1, N'p10', N'b10', N'recipe4sec220211129030358', N'CRS 50L', N'CRS 50L', N'Operation', N'recipe4sec2',DATEADD(ss, -4, GETUTCDATE()), GETUTCDATE(), N'Bio4cService', N'Completed', N'pre comments 10', N'post comments 10', N'', N'', N'', N'', N'', DATEADD(ss, -4, GETUTCDATE()), GETUTCDATE())
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (2, N'p11', N'b11', N'recipe4sec220211129035111', N'CRS 50L', N'CRS 50L', N'Operation', N'recipe4sec2', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()), N'Bio4cService', N'Aborted', N'pre comm', N'last c', N'', N'', N'', N'', N'', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()))
GO
SET IDENTITY_INSERT [dbo].[runheader] OFF
GO
DELETE FROM [dbo].[report_template]
GO
SET IDENTITY_INSERT [dbo].[report_template] ON
GO
INSERT [dbo].[report_template] ([report_template_id], [report_template_name], [report_template_Json], [report_template_created_on], [report_template_created_by], [report_template_state], [report_template_status], [lastModifiedBy], [lastModifiedOn], [eSignatureCode], [approver], [signature_count], [template_type], [device_id], [is_default]) VALUES (1, N'testReportTemplate', N'[{"name":"Run Header","identifier":"runHeader","checked":true,"disabled":true},{"name":"Pre Run Data","identifier":"preRunData","checked":true},{"name":"Audit Trail","identifier":"auditTrail","checked":true},{"name":"Run Summary","identifier":"runSummary","checked":true},{"name":"Alarms","identifier":"alarms","checked":true,"value":[{"name":"Events","identifier":"events","value":[{"name":"Process","identifier":"process","checked":false},{"name":"System","identifier":"system","checked":false}]}]},{"name":"Trends","identifier":"chromatograms","checked":true,"value":[{"name":"TT","identifier":"Id_0","value":[{"identifier":"AI001","name":"AI001.Obj.Out_rCurrentValue","description":"External AI 01","checked":true},{"identifier":"AI002","name":"AI002.Obj.Out_rCurrentValue","description":"External AI 02","checked":true},{"identifier":"CSPR1","name":"CSPR1.Obj.Out_rCurrentValue","description":"Active Permeate CSPR","checked":true}]}]},{"name":"Event Summary","identifier":"eventSummary","checked":true}]', CAST(N'2021-10-26T09:13:01.000' AS DateTime), N'Bio4CAdmin', N'true', N'Approved', N'Bio4CAdmin', CAST(N'2021-10-26T09:13:37.000' AS DateTime), N'ec7c7ce3f5086b682459ce0c8d75530a4ec658e2ee4b99f0e231bf0558e9733da9c84643a19b18ad3c615f3fb7f4aa4b75ad6d16cde72a79afd0f72079401a1f', N'Bio4CAdmin', 1, N'RunSummary', N'CRS 50L', N'n')
GO
SET IDENTITY_INSERT [dbo].[report_template] OFF
GO