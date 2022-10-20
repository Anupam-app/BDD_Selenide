USE [recipe]
GO
DELETE FROM [dbo].[recipe_table]
GO
SET IDENTITY_INSERT [dbo].[recipe_table] ON 
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2021', GETUTCDATE(), N'testDraftRecipeToChangeStatus', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 12:47:03 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceSize":"50L","deviceUoPVersion":"2022","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"testDraftRecipeToChangeStatus","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdBy":"Bio4CAdmin","createdDate":"10/28/2021 12:47:03 PM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":50,"valueType":"userDefined","rangeLo":0,"value":"1"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 5, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2021', GETUTCDATE(), N'testDraftRecipeToAddPhase', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 08:26:31 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceSize":"50L","deviceUoPVersion":"2022","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"VFDEhzwqPb","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdDate":"10/28/2021 08:00:38 PM","createdBy":"Bio4CAdmin","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":12,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_jxKM6TgcEeymTqpjPRWOaA","rangeLo":0,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"displayMode":"actionValueNone","nodes":[{"name":"Phases","key":"0|0800000000"},{"name":"cwtmwIQxda","key":"0|0803000000"},{"name":"Start","key":"0|0803010000A"}]}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]},{"phaseNumber":3,"phaseName":"cwtmwIQxda","phaseKey":"0|0803000000","id":11,"steps":[{"phaseName":"cwtmwIQxda","phaseNumber":3,"stepNumber":1,"signature":"none","id":10,"actionType":"simple","actionBlock":[{"displayMode":"","complete":8,"value":null,"nodes":[]}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 7, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2021', GETUTCDATE(), N'testDraftRecipe', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 12:47:03 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceSize":"50L","deviceUoPVersion":"2022","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"testDraftRecipe","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdBy":"Bio4CAdmin","createdDate":"10/28/2021 12:47:03 PM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":50,"valueType":"userDefined","rangeLo":0,"value":"1"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 1, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2022', CAST(N'2022-08-29T11:08:54.9260000' AS DateTime2), N'testRecipeToExecute1min', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"08/29/2022 11:08:54 AM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceUoPVersion":"2022","deviceShapeVersion":"2022.0.1.154/Wed Aug 17 01:07:29 CDT 2022","recipeName":"testRecipeToExecute1min","recipeVersion":"2022","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"61","recipeState":"Approved-Active","createdBy":"Bio4CAdmin","createdDate":"08/29/2022 11:08:32 AM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":4.0,"valueType":"userDefined","rangeLo":0.0,"value":"1.0"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2022', NULL, 10219, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2022', CAST(N'2022-09-05T10:20:05.0380000' AS DateTime2), N'testRecipeFlows', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"09/05/2022 10:20:05 AM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceUoPVersion":"2022","deviceShapeVersion":"1.0.11.25/Tue Nov 30 19:11:08 CET 2021","recipeName":"testRecipeFlows","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"7","recipeState":"Approved-Active","createdBy":"Bio4CAdmin","createdDate":"09/05/2022 10:19:31 AM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":4.0,"valueType":"userDefined","rangeLo":0.0,"value":"1.0"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"id":2,"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":4.0,"valueType":"userDefined","rangeLo":0.0,"value":"1.0"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"id":3,"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":4.0,"valueType":"userDefined","rangeLo":0.0,"value":"1.0"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10225, NULL, N'Approved-Active', N'Bio4CAdmin', 1, CAST(N'2021-12-22T12:53:02.0000000' AS DateTime2))
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRS Family', 0, N'CRSSubFamily', N'Mobius® Cell Retention System', N'2022', GETUTCDATE(), N'testRecipeToExecute', N'{"recipe":{"operationHeader":{"machineName":"Mobius® Cell Retention System","productIdentification":"CRS","description":"CRS","lastSavedOn":"09/05/2022 10:20:42 AM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRS Family","deviceSubFamily":"CRSSubFamily","deviceUoP":"Mobius® Cell Retention System","deviceUoPVersion":"2022","deviceShapeVersion":"2022.0.1.162/Fri Sep 02 07:27:08 CDT 2022","recipeName":"testRecipeToExecute","recipeVersion":"2022","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Approved-Active","createdBy":"Bio4CAdmin","createdDate":"09/05/2022 10:20:17 AM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":4.0,"valueType":"userDefined","rangeLo":0.0,"value":"1.0"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2022', NULL, 10226, NULL, N'Approved-Active', N'Bio4CAdmin', 1, CAST(N'2022-02-02T10:06:15.0000000' AS DateTime2))
GO
SET IDENTITY_INSERT [dbo].[recipe_table] OFF
GO