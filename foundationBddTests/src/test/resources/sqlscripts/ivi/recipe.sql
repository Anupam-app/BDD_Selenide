USE [recipe]
GO
DELETE FROM [dbo].[recipe_table]
GO
SET IDENTITY_INSERT [dbo].[recipe_table] ON
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testDraftRecipeToChangeStatus', N'{"recipe":{"operationHeader":{"machineName":"IVI","productIdentification":"IVI","description":"IVI","lastSavedOn":"05/05/2022 03:36:14 PM","lastSavedBy":"Bio4cService","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"Merck Inline Virus Inactivation","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.3.47/Fri Apr 29 19:04:05 CEST 2022","recipeName":"testDraftRecipeToChangeStatus","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdDate":"05/05/2022 03:35:54 PM","createdBy":"Bio4cService","locked":true,"approvedBy":"Bio4cService","approvedOn":"05/05/2022-03:36:14:553","eSignComment":"","eSignCode":"bf230b990eee94210afe7ddcd9606f86be3ddd7c104e996c264d4e5da584c2fafaf9ad059a73534720c4ff883515ccd52e8c059507a33e2b104ea689b5958388"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":1,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH5sx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"3.69","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02030103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH38x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02030101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH48x3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02030102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_cx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_8x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 5, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testDraftRecipeToAddPhase', N'{"recipe":{"operationHeader":{"machineName":"IVI","productIdentification":"IVI","description":"IVI","lastSavedOn":"05/05/2022 03:36:14 PM","lastSavedBy":"Bio4cService","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"Merck Inline Virus Inactivation","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.3.47/Fri Apr 29 19:04:05 CEST 2022","recipeName":"testDraftRecipeToAddPhase","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdDate":"05/05/2022 03:35:54 PM","createdBy":"Bio4cService","locked":true,"approvedBy":"Bio4cService","approvedOn":"05/05/2022-03:36:14:553","eSignComment":"","eSignCode":"bf230b990eee94210afe7ddcd9606f86be3ddd7c104e996c264d4e5da584c2fafaf9ad059a73534720c4ff883515ccd52e8c059507a33e2b104ea689b5958388"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":1,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH5sx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"3.69","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02030103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH38x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02030101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH48x3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02030102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_cx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_8x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 7, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testDraftRecipe', N'{"recipe":{"operationHeader":{"machineName":"IVI","productIdentification":"IVI","description":"IVI","lastSavedOn":"05/05/2022 03:36:14 PM","lastSavedBy":"Bio4cService","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"Merck Inline Virus Inactivation","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.3.47/Fri Apr 29 19:04:05 CEST 2022","recipeName":"testDraftRecipe","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"3","recipeState":"Draft","createdDate":"05/05/2022 03:35:54 PM","createdBy":"Bio4cService","locked":true,"approvedBy":"Bio4cService","approvedOn":"05/05/2022-03:36:14:553","eSignComment":"","eSignCode":"bf230b990eee94210afe7ddcd9606f86be3ddd7c104e996c264d4e5da584c2fafaf9ad059a73534720c4ff883515ccd52e8c059507a33e2b104ea689b5958388"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":1,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH5sx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"3.69","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02030103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH38x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02030101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3ttH48x3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02030000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02030100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02030102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_cx3Eey0KKAJQnp-tQ","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_3tuV_8x3Eey0KKAJQnp-tQ","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Draft', N'2021', NULL, 1, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testRecipeToExecute', N'{"recipe":{"operationHeader":{"machineName":"IVI","lastSavedOn":"07/10/2022 04:40:38 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"IVI","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.5.91/Tue Jul 05 08:49:25 CEST 2022","recipeName":"testRecipeToExecute","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"2","recipeState":"Approved-Active","createdDate":"07/10/2022 04:40:08 PM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"07/10/2022-04:40:37:739","eSignComment":"","eSignCode":"c689e8242d6b11f81491b5a50de52e51cddecdcec3cbaea953d887080144b558fdd9a2b32ad1a00f73f52c7e5d08d3cb52f3fc7eec9dff067f394e0438486d77"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsVABdEe25cO2PDT2abw","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"1","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02050103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsTQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02050101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsUQBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02050102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62elAQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":6,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ek_QBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10099, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testRecipeFlows', N'{"recipe":{"operationHeader":{"machineName":"IVI","lastSavedOn":"07/10/2022 04:40:38 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"IVI","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.5.91/Tue Jul 05 08:49:25 CEST 2022","recipeName":"testRecipeFlows","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"5","recipeState":"Approved-Active","createdDate":"07/10/2022 04:40:08 PM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"07/10/2022-04:40:37:739","eSignComment":"","eSignCode":"c689e8242d6b11f81491b5a50de52e51cddecdcec3cbaea953d887080144b558fdd9a2b32ad1a00f73f52c7e5d08d3cb52f3fc7eec9dff067f394e0438486d77"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsVABdEe25cO2PDT2abw","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"1","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02050103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsTQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02050101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsUQBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02050102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62elAQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":6,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ek_QBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10098, NULL, NULL, NULL, 0, NULL)
GO
INSERT [dbo].[recipe_table] ([author], [ccp_procedure_id], [ccp_recipe_location], [configuration], [device_family], [system_id], [device_sub_family], [device_uop], [device_uop_ver], [modified_date], [recipe_name], [recipe], [status], [version], [dispacthed_date], [id], [device_id], [import_status], [import_created_by], [imported], [import_modified_date]) VALUES (N'Bio4CAdmin', NULL, NULL, NULL, N'IVIFamily', 0, N'IVISubFamily', N'IVI', N'2021', GETUTCDATE(), N'testRecipeToExecute1min', N'{"recipe":{"operationHeader":{"machineName":"IVI","lastSavedOn":"07/10/2022 04:40:38 PM","lastSavedBy":"Bio4CAdmin","comment":"","systemId":0,"deviceFamily":"IVIFamily","deviceSubFamily":"IVISubFamily","deviceUoP":"IVI","deviceUoPVersion":"2021","deviceShapeVersion":"2022.1.5.91/Tue Jul 05 08:49:25 CEST 2022","recipeName":"testRecipeToExecute1min","recipeVersion":"2021","recipeEditorVersion":"R1.1.1-PI1.4","recipeFormatVersion":"V30","defaultStepWaitTime":"12","recipeState":"Approved-Active","createdDate":"07/10/2022 04:40:08 PM","createdBy":"Bio4CAdmin","locked":true,"approvedBy":"Bio4CAdmin","approvedOn":"07/10/2022-04:40:37:739","eSignComment":"","eSignCode":"c689e8242d6b11f81491b5a50de52e51cddecdcec3cbaea953d887080144b558fdd9a2b32ad1a00f73f52c7e5d08d3cb52f3fc7eec9dff067f394e0438486d77"},"phases":[{"phaseNumber":0,"phaseName":"","phaseKey":"","id":0,"steps":[{"phaseName":"","phaseNumber":0,"stepNumber":1,"signature":"none","id":2,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsVABdEe25cO2PDT2abw","rangeLo":0,"rangeHi":5.17,"valueType":"userDefined","value":"1","egu":"bar","dataType":"Float"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop Setpoint","key":"0|02050103A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_setpoint"}],"displayMode":"actionValueEGURange"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":2,"signature":"none","id":3,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsTQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop ON","key":"0|02050101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_on"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":3,"signature":"none","id":4,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ZsUQBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Product Inlet","key":"0|02000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"Control loop","key":"0|02050000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.control_loop"},{"name":"Pressure Loop","key":"0|02050100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_loop"},{"name":"Pressure Control Loop OFF","key":"0|02050102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.pressure_control_loop_off"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":4,"signature":"none","id":5,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62elAQBdEe25cO2PDT2abw","rangeLo":1,"rangeHi":1,"valueType":"readonly","value":"1.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Open","key":"0|06010102A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.open"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""},{"phaseName":"","phaseNumber":0,"stepNumber":5,"signature":"none","id":6,"actionType":"simple","actionBlock":[{"complete":0,"value":{"eClass":"http://bio4c.merckgroup.com/deviceshapeclient/recipe/actioncriteria/v1.0#//ActionValue","_id":"_62ek_QBdEe25cO2PDT2abw","rangeLo":0,"rangeHi":0,"valueType":"readonly","value":"0.0","dataType":"Boolean"},"nodes":[{"name":"Valves","key":"0|06000000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.valves"},{"name":"Product Inlet","key":"0|06010000","actionType":"simple","translationKey":"IVI.ds.recipe.actions.product_inlet"},{"name":"XV001","key":"0|06010100","actionType":"simple","translationKey":"IVI.ds.recipe.actions.xv001"},{"name":"Close","key":"0|06010101A","actionType":"simple","translationKey":"IVI.ds.recipe.actions.close"}],"displayMode":"actionValueNone"}],"criteriaBlock":[],"comment":"","parameterTab":"","parameterScope":"","stepWaitTime":""}]}]}}', N'Approved-Active', N'2021', NULL, 10097, NULL, NULL, NULL, 0, NULL)
GO
SET IDENTITY_INSERT [dbo].[recipe_table] OFF
GO
