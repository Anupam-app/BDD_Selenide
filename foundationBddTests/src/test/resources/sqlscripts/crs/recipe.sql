USE [recipe]
GO
DELETE FROM [dbo].[recipe_table]
GO
SET IDENTITY_INSERT [dbo].[recipe_table] ON 
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testDraftRecipeToChangeStatus', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 12:47:03 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"testDraftRecipeToChangeStatus","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdBy":"Bio4CAdmin","createdDate":"10/28/2021 12:47:03 PM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":50,"valueType":"userDefined","rangeLo":0,"value":"1"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 5, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testRecipeToExecute', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"02/02/2022 10:06:38 AM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.12.50/Mon Jan 31 13:51:46 CST 2022","recipeName":"testRecipeToExecute","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Approved-Active","createdDate":"02/02/2022 10:06:15 AM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"02/02/2022-10:06:37:873","eSignComment":"","eSignCode":"69dae6f44ca67775c78acacb17a65049a784a2b37e0c6318888919ff9b87519c90b3c242a3e99dbbbd0018e8d59be5edbe70382cf82487ef74693b9e6f88780a"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":1,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_97u_ZIRBEeyvwapbGkNKEA","rangeLo":0,"rangeHi":4,"valueType":"userDefined","value":"1.0","egu":"L/min","dataType":"Float"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10077, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testDraftRecipeToAddPhase', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 08:26:31 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"VFDEhzwqPb","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdDate":"10/28/2021 08:00:38 PM","createdBy":"Bio4CAdmin","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":12,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_jxKM6TgcEeymTqpjPRWOaA","rangeLo":0,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"displayMode":"actionValueNone","nodes":[{"name":"Phases","key":"0|0800000000"},{"name":"cwtmwIQxda","key":"0|0803000000"},{"name":"Start","key":"0|0803010000A"}]}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]},{"phaseNumber":3,"phaseName":"cwtmwIQxda","phaseKey":"0|0803000000","id":11,"steps":[{"phaseName":"cwtmwIQxda","phaseNumber":3,"stepNumber":1,"signature":"none","id":10,"actionType":"simple","actionBlock":[{"displayMode":"","complete":8,"value":null,"nodes":[]}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 7, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testRecipeFlows', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"12/22/2021 12:53:28 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.11.25/Tue Nov 30 19:11:08 CET 2021","recipeName":"testRecipeFlows","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"7","recipeState":"Approved-Active","createdDate":"12/22/2021 12:53:02 PM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"12/22/2021-12:53:28:517","eSignComment":"","eSignCode":"b8e030d09238a60bc29c447c14b3e1e430fd32fba44bb8f79083a8f0f2e6ca11ff9422f95c530abe79233c8c797b253bee1fd89ef3058424f6511f57962bd6a4"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_97u_ZIRBEeyvwapbGkNKEA","rangeLo":0,"rangeHi":4,"valueType":"userDefined","value":"1.0","egu":"L/min","dataType":"Float"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"id":2,"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_97u_ZIRBEeyvwapbGkNKEA","rangeLo":0,"rangeHi":4,"valueType":"userDefined","value":"1.0","egu":"L/min","dataType":"Float"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"id":3,"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_97u_ZIRBEeyvwapbGkNKEA","rangeLo":0,"rangeHi":4,"valueType":"userDefined","value":"1.0","egu":"L/min","dataType":"Float"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10095, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testDraftRecipe', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"10/28/2021 12:47:03 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.10/Fri Oct 22 15:38:40 CEST 2021","recipeName":"testDraftRecipe","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdBy":"Bio4CAdmin","createdDate":"10/28/2021 12:47:03 PM","locked":false,"approvedBy":"","approvedOn":"","eSignComment":"","eSignCode":""},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"id":1,"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","actionType":"simple","actionBlock":[{"complete":0,"value":{"egu":"L/min","rangeHi":50,"valueType":"userDefined","rangeLo":0,"value":"1"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 1, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'CRSFamily', 0, N'CRSSubFamily', N'CRS 50L', N'2021', GETUTCDATE(), N'testRecipeToExecute1min', N'{"recipe":{"operationHeader":{"machineName":"CRS 50L","productIdentification":"CRS","description":"CRS","lastSavedOn":"02/02/2022 10:06:38 AM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"CRSFamily","deviceSubFamily":"CRSSubFamily","deviceUoP":"CRS 50L","deviceUoPVersion":"2021","deviceShapeVersion":"1.0.12.50/Mon Jan 31 13:51:46 CST 2022","recipeName":"testRecipeToExecute1min","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"61","recipeState":"Approved-Active","createdDate":"02/02/2022 10:06:15 AM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"02/02/2022-10:06:37:873","eSignComment":"","eSignCode":"69dae6f44ca67775c78acacb17a65049a784a2b37e0c6318888919ff9b87519c90b3c242a3e99dbbbd0018e8d59be5edbe70382cf82487ef74693b9e6f88780a"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":1,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_97u_ZIRBEeyvwapbGkNKEA","rangeLo":0,"rangeHi":4,"valueType":"userDefined","value":"1.0","egu":"L/min","dataType":"Float"},"nodes":[{"name":"Control Loop","key":"0|0100000000","actionType":"simple"},{"name":"Feed Flow FI101","key":"0|0101000000","actionType":"simple"},{"name":"Configure","key":"0|0101010000","actionType":"simple"},{"name":"Setpoint","key":"0|0101010100A","actionType":"simple"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10080, NULL, NULL, NULL, 0, NULL)
GO
SET IDENTITY_INSERT [dbo].[recipe_table] OFF
GO