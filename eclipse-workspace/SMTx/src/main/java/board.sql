USE [Notice]
GO

/****** Object:  Table [dbo].[CS_Ans]    Script Date: 2023-08-24 오전 9:53:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CS_Ans](
	[Board_ID] [int] NOT NULL,
	[Comment_ID] [nvarchar](20) NOT NULL,
	[Answer_ID] [nvarchar](20) NULL,
	[Contents] [nvarchar](1000) NULL,
	[File_Name] [varbinary](max) NULL,
	[Ins_Date_Time] [datetime] NULL,
	[Upd_Date_Time] [datetime] NULL,
	[Del_Date_Time] [datetime] NULL,
	[Del_Yn] [char](1) NULL,
 CONSTRAINT [PK_CS_Ans] PRIMARY KEY CLUSTERED 
(
	[Board_ID] ASC,
	[Comment_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

INSERT INTO [CS_Ans] values()
SELECT * FROM [CS_Ans];


/*
 * 
 * 
 * USE [Notice]
GO

/****** Object:  Table [dbo].[CS_Ques]    Script Date: 2023-08-24 오전 9:53:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CS_Ques](
	[Board_ID] [int] IDENTITY(1,1) NOT NULL,
	[Comment_ID] [nvarchar](20) NOT NULL,
	[Title] [nvarchar](20) NULL,
	[Contents] [nvarchar](1000) NULL,
	[File_Name] [varbinary](max) NULL,
	[Ins_Date_Time] [datetime] NULL,
	[Upd_Date_Time] [datetime] NULL,
	[Del_Date_Time] [datetime] NULL,
	[Del_Yn] [char](1) NULL,
 CONSTRAINT [PK_CS_Ques] PRIMARY KEY CLUSTERED 
(
	[Board_ID] ASC,
	[Comment_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO



 * */
