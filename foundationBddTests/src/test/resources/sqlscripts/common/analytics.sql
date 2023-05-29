USE [Analytics]
GO
DELETE FROM [dbo].[aggregate_run]
GO
DELETE FROM [dbo].[parameter]
GO
DELETE FROM [dbo].[aggregate]
GO
SET IDENTITY_INSERT [dbo].[aggregate] ON
GO
INSERT [dbo].[aggregate] ([aggregate_id],[user_id],[frequency],[batch_id],[aggregate_name],[last_agg_timestamp],[status]) VALUES (58,N'Bio4CAdmin',3,N'b10',N'testAggregate',N'01/Feb/2023 11:58:54',N'Completed')
GO
SET IDENTITY_INSERT [dbo].[aggregate] OFF
GO
INSERT [dbo].[aggregate_run] ([aggregate_id],[run_id]) VALUES (58, N'recipe4sec220211129030358')
GO
INSERT [dbo].[parameter] ([aggregate_id],[parameter_id]) VALUES (58,N'PI101')
GO
INSERT [dbo].[parameter] ([aggregate_id],[parameter_id]) VALUES (58,N'PI102')
GO
