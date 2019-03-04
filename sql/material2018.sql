
/**if not EXISTS (SELECT * FROM sys.tables WHERE [NAME] = 材料库2018概算)**/
CREATE TABLE [dbo].[材料库2018概算] (
	[文号] [nvarchar](50) NOT NULL,
	[电算代号] [int] NOT NULL,
	[材料名称] [nvarchar](50) NULL,
	[单位] [nvarchar](20) NULL,
	[基期单价] [float] NULL,
	[编制期价] [float] NULL,
	[单重] [float] NULL,
	[换算系数] [float] NULL,
	[汇总标志] [nvarchar](50) NULL,
	[主材标志] [char](1) NULL,
	[材料运输类别] [nvarchar](50) NULL,
	[LOCK] [bit] NULL,
	[甲供方式] [nvarchar](200) NULL,
 CONSTRAINT [PK_材料库2018概算] PRIMARY KEY CLUSTERED
(
	[文号] ASC,
	[电算代号] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
