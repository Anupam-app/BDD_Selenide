USE [ReportManagement]
GO
DELETE FROM [dbo].[runheader]
GO
SET IDENTITY_INSERT [dbo].[runheader] ON
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (1, N'p10', N'b10', N'recipe4sec220211129030358', N'CRS 50L', N'CRS 50L', N'Operation', N'recipe4sec2',DATEADD(hh, -9, GETUTCDATE()), DATEADD(hh, -9, GETUTCDATE()), N'Bio4cService', N'Completed', N'pre comments 10', N'post comments 10', N'', N'', N'', N'', N'', DATEADD(ss, -4, GETUTCDATE()), GETUTCDATE())
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (2, N'p11', N'b11', N'recipe4sec220211129035111', N'CRS 50L', N'CRS 50L', N'Operation', N'recipe4sec2', DATEADD(hh, -10, GETUTCDATE()), DATEADD(hh, -6, GETUTCDATE()), N'Bio4cService', N'Aborted', N'pre comm', N'last c', N'', N'', N'', N'', N'', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()))
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (3, N'p12', N'b10', N'recipe10sec220211129035112', N'CRS 50L', N'CRS 50L', N'Operation', N'recipe4sec2', DATEADD(hh, -10, GETUTCDATE()), DATEADD(hh, -6, GETUTCDATE()), N'Bio4cService', N'Aborted', N'pre comm', N'last c', N'', N'', N'', N'', N'', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()))
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (4, N'p13', N'b12', N'testManualRun1', N'CRS 50L', N'CRS 50L', N'Manual', N'recipe4sec3', DATEADD(hh, -10, GETUTCDATE()), DATEADD(hh, -6, GETUTCDATE()), N'Bio4cService', N'Aborted', N'pre comm', N'last c', N'', N'', N'', N'', N'', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()))
GO
INSERT [dbo].[runheader] ([id], [product_id], [batch_id], [run_id], [device_id], [machine_name], [recipe_procedure], [file_name], [start_date_time], [end_date_time], [user_name], [run_state], [preRun_comment], [postRun_comment], [step_id], [column_info], [packedBed_info], [sample_info], [buffer_info], [cip_start_time], [cip_end_time]) VALUES (5, N'p14', N'b12', N'testManualRun2', N'CRS 50L', N'CRS 50L', N'Manual', N'recipe4sec4', DATEADD(hh, -10, GETUTCDATE()), DATEADD(hh, -6, GETUTCDATE()), N'Bio4cService', N'Aborted', N'pre comm', N'last c', N'', N'', N'', N'', N'', DATEADD(ss, -10, GETUTCDATE()), DATEADD(ss, -6, GETUTCDATE()))
GO
SET IDENTITY_INSERT [dbo].[runheader] OFF
GO
DELETE FROM [dbo].[report_template]
GO
SET IDENTITY_INSERT [dbo].[report_template] ON
GO
INSERT [dbo].[report_template] ([report_template_id], [report_template_name], [report_template_Json], [report_template_created_on], [report_template_created_by], [report_template_state], [report_template_status], [lastModifiedBy], [lastModifiedOn], [eSignatureCode], [approver], [signature_count], [template_type], [device_id], [is_default]) VALUES (1, N'testReportTemplate', N'[{"name":"Run Header","identifier":"runHeader","checked":true,"disabled":true,"translationKey":"report.list.runHeader"},{"name":"Audit Trail","identifier":"auditTrail","checked":true,"disabled":false,"translationKey":"report.common.list.auditTrail"},{"name":"Run Summary","identifier":"runSummary","checked":true,"disabled":false,"translationKey":"report.common.list.runSummary"},{"name":"Alarms","identifier":"alarms","checked":true,"disabled":false,"value":[{"name":"Events","identifier":"events","checked":false,"disabled":false,"value":[{"name":"Process","identifier":"process","checked":true,"disabled":false,"translationKey":"report.list.modal.process"},{"name":"System","identifier":"system","checked":true,"disabled":false,"translationKey":"report.list.modal.system"}],"translationKey":"report.list.modal.events"}],"translationKey":"report.common.alarms"},{"name":"Trends","identifier":"chromatograms","checked":false,"disabled":false,"value":[],"translationKey":"report.common.trends"},{"name":"Event Summary","identifier":"eventSummary","checked":false,"disabled":false,"translationKey":"report.list.eventSummary"},{"name":"Calibration Summary","identifier":"calibrationSummary","checked":false,"disabled":false,"translationKey":"report.list.calibrationSummary"}]', GETUTCDATE(), N'Bio4CAdmin', N'true', N'Approved', N'Bio4CAdmin', GETUTCDATE(), N'ec7c7ce3f5086b682459ce0c8d75530a4ec658e2ee4b99f0e231bf0558e9733da9c84643a19b18ad3c615f3fb7f4aa4b75ad6d16cde72a79afd0f72079401a1f', N'Bio4CAdmin', 1, N'RunSummary', N'CRS 50L', N'n')
GO
INSERT [dbo].[report_template] ([report_template_id], [report_template_name], [report_template_Json], [report_template_created_on], [report_template_created_by], [report_template_state], [report_template_status], [lastModifiedBy], [lastModifiedOn], [eSignatureCode], [approver], [signature_count], [template_type], [device_id], [is_default]) VALUES (10, N'testDraftTemplate', N'[{"name":"Run Header","identifier":"runHeader","checked":true,"disabled":true,"translationKey":"report.list.runHeader"},{"name":"Audit Trail","identifier":"auditTrail","checked":true,"disabled":false,"translationKey":"report.common.list.auditTrail"},{"name":"Run Summary","identifier":"runSummary","checked":true,"disabled":false,"translationKey":"report.common.list.runSummary"},{"name":"Alarms","identifier":"alarms","checked":false,"disabled":false,"value":[{"name":"Events","identifier":"events","checked":false,"disabled":false,"value":[{"name":"Process","identifier":"process","checked":false,"disabled":false,"translationKey":"report.list.modal.process"},{"name":"System","identifier":"system","checked":false,"disabled":false,"translationKey":"report.list.modal.system"}],"translationKey":"report.list.modal.events"}],"translationKey":"report.common.alarms"},{"name":"Trends","identifier":"chromatograms","checked":false,"disabled":false,"value":[],"translationKey":"report.common.trends"},{"name":"Event Summary","identifier":"eventSummary","checked":false,"disabled":false,"translationKey":"report.list.eventSummary"},{"name":"Calibration Summary","identifier":"calibrationSummary","checked":false,"disabled":false,"translationKey":"report.list.calibrationSummary"}]', GETUTCDATE(), N'Bio4CAdmin', N'true', N'Draft', N'Bio4CAdmin', GETUTCDATE(), N'', N'', 1, N'RunSummary', N'CRS 50L', N'n')
GO
SET IDENTITY_INSERT [dbo].[report_template] OFF
GO
DELETE FROM [dbo].[report_repository]
GO
SET IDENTITY_INSERT [dbo].[report_repository] ON
GO
INSERT [dbo].[report_repository] ([report_id], [report_name], [created_by], [cause], [state], [status], [file_name], [template_id], [generation_executed_time], [template_version], [generation_triggered_time], [report_type], [esign_status], [signed_by], [signed_on], [esign_code], [drweb_url], [hetp_report_id]) VALUES (1, N'AuditTrail_1_Bio4CAdmin', N'Bio4CAdmin', N'manual', N'COMPLETED', N'SUCCESS', N'AuditTrail_1.pdf', NULL, GETUTCDATE(), N'', GETUTCDATE(), N'AuditTrail', N'Signed', N'Bio4CAdmin', GETUTCDATE(), N'43de7e13171d309d8cf13d151495980dbd779c5def672ff37a7a03c56f8a2f87277bdb59402adf991b03943b65d6321287a548db57ce4f2d12d78b5a1cff8688', N'http://localhost:4430/DRWeb/WebAPI/GenReport.aspx?report=Foundn_AuditTrail&format=pdf&reportTitle=%22Audit+Trail%22&StartDate=%222022-02-17+00%3A00%3A00%22&EndDate=%222022-02-17+23%3A59%3A59%22&userId=%22Bio4CAdmin%22&reportType=%22AuditTrail%22&fileName=%22AuditTrail_16%22&reportId=16&auditTrailUser=%22All%22&genTime=%2217%2FFeb%2F2022+04%3A27%3A41%22&timeZone=%22Central+Standard+Time%22&genTime=%2217%2FFeb%2F2022+10%3A27%3A41%22', NULL)
GO
INSERT [dbo].[report_repository] ([report_id], [report_name], [created_by], [cause], [state], [status], [file_name], [template_id], [generation_executed_time], [template_version], [generation_triggered_time], [report_type], [esign_status], [signed_by], [signed_on], [esign_code], [drweb_url], [hetp_report_id]) VALUES (18, N'RunSummary_18_Bio4CAdmin', N'Bio4CAdmin', N'manual', N'COMPLETED', N'SUCCESS', N'RunSummary_18_Bio4CAdmin.pdf', 1, GETUTCDATE(), N'', GETUTCDATE(), N'RunSummary', N'Signed', N'Bio4CAdmin', GETUTCDATE(), N'26eddeab805f8e094ff6f161c73613df219bc1adabc3897795e03a6e48b0a10fd44545386e047caa63e14976542db8b2b0c1f354e097b127deab1e4949c020b1', N'http://localhost:4430/DRWeb/WebAPI/GenReport.aspx?report=Foundn_RunSummary&format=pdf&reportTitle=%22Run+Summary%22&StartDate=%222022-02-17+05%3A27%3A37%22&EndDate=%222022-02-17+05%3A27%3A41%22&userId=%22Bio4CAdmin%22&reportType=%22RunSummary%22&fileName=%22RunSummary_18_Bio4CAdmin%22&reportId=18&templateName=%22testReportTemplate%22&offSetMins=0&deviceId=%22CRS+50L%22&HETPId=0&trendST=&trendET=&genTime=%2217%2FFeb%2F2022+07%3A20%3A03%22&timeZone=%22Central+Standard+Time%22&genTime=%2217%2FFeb%2F2022+13%3A20%3A03%22&userName=%22Administrator+Bio4C%22&signOn=%2217%2FFeb%2F2022+07%3A23%3A00%22', NULL)
GO
SET IDENTITY_INSERT [dbo].[report_repository] OFF
GO
DELETE FROM [dbo].[report_template_section]
GO
SET IDENTITY_INSERT [dbo].[report_template_section] ON 
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2823, N'runHeader', N'Run Header', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2824, N'preRunData', N'Pre Run Data', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2825, N'auditTrail', N'Audit Trail', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2826, N'runSummary', N'Run Summary', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2827, N'alarms', N'Alarms', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2828, N'events', N'Events', NULL, 0, 1, 2827)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2829, N'process', N'Process', NULL, 0, 1, 2828)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2830, N'system', N'System', NULL, 0, 1, 2828)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2831, N'chromatograms', N'Trends', NULL, 1, 1, NULL)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2832, N'Id_0', N'TT', NULL, 0, 1, 2831)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2833, N'AI001', N'AI001.Obj.Out_rCurrentValue', NULL, 1, 1, 2832)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2834, N'AI002', N'AI002.Obj.Out_rCurrentValue', NULL, 1, 1, 2832)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2835, N'CSPR1', N'CSPR1.Obj.Out_rCurrentValue', NULL, 1, 1, 2832)
GO
INSERT [dbo].[report_template_section] ([id], [config_name], [config_value], [config_value_numeric], [config_isChecked], [report_template_id], [parent_id]) VALUES (2836, N'eventSummary', N'Event Summary', NULL, 0, 1, NULL)
GO
SET IDENTITY_INSERT [dbo].[report_template_section] OFF
GO

