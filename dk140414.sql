
CREATE TABLE [City]
( 
	[IDCity]             integer  IDENTITY  NOT NULL ,
	[Name]               varchar(100)  NOT NULL ,
	[PostalCode]         varchar(100)  NOT NULL 
)
go

ALTER TABLE [City]
	ADD CONSTRAINT [XPKCity] PRIMARY KEY  CLUSTERED ([IDCity] ASC)
go

ALTER TABLE [City]
	ADD CONSTRAINT [XAK1City] UNIQUE ([Name]  ASC)
go

ALTER TABLE [City]
	ADD CONSTRAINT [XAK2City] UNIQUE ([PostalCode]  ASC)
go

CREATE TABLE [Courier]
( 
	[Status]             integer  NULL 
	CONSTRAINT [CourierStatusValues_1010847340]
		CHECK  ( [Status]=0 OR [Status]=1 ),
	[Deliveries]         integer  NULL ,
	[IDUser]             integer  NOT NULL ,
	[Profit]             decimal(10,3)  NULL ,
	[IDVehicle]          integer  NULL 
)
go

ALTER TABLE [Courier]
	ADD CONSTRAINT [XPKCourier] PRIMARY KEY  CLUSTERED ([IDUser] ASC)
go

CREATE TABLE [CourierRequest]
( 
	[IDUser]             integer  NOT NULL ,
	[IDVehicle]          integer  NOT NULL 
)
go

ALTER TABLE [CourierRequest]
	ADD CONSTRAINT [XPKCourierRequest] PRIMARY KEY  CLUSTERED ([IDUser] ASC)
go

CREATE TABLE [District]
( 
	[Name]               varchar(100)  NOT NULL ,
	[XPos]               integer  NULL ,
	[YPos]               integer  NULL ,
	[IDCity]             integer  NOT NULL ,
	[IDDistrict]         integer  IDENTITY  NOT NULL 
)
go

ALTER TABLE [District]
	ADD CONSTRAINT [XPKDistrict] PRIMARY KEY  CLUSTERED ([IDDistrict] ASC)
go

CREATE TABLE [Drive]
( 
	[IDUser]             integer  NOT NULL ,
	[IDPackage]          integer  NOT NULL 
)
go

ALTER TABLE [Drive]
	ADD CONSTRAINT [XPKDrive] PRIMARY KEY  CLUSTERED ([IDUser] ASC,[IDPackage] ASC)
go

CREATE TABLE [Package]
( 
	[IDPackage]          integer  IDENTITY  NOT NULL ,
	[IDDistrict1]        integer  NULL ,
	[IDDistrict2]        integer  NULL ,
	[IDUser]             integer  NULL ,
	[Type]               integer  NULL 
	CONSTRAINT [PackageTypeValues_790844993]
		CHECK  ( [Type]=0 OR [Type]=1 OR [Type]=2 ),
	[Weight]             decimal(10,3)  NULL ,
	[DeliveryStatus]     integer  NULL 
	CONSTRAINT [PackageDeliveryStatusValues_1075649309]
		CHECK  ( [DeliveryStatus]=0 OR [DeliveryStatus]=1 OR [DeliveryStatus]=2 OR [DeliveryStatus]=3 ),
	[TimeAccepted]       datetime  NULL ,
	[DeliveryPrice]      decimal(10,3)  NULL 
)
go

ALTER TABLE [Package]
	ADD CONSTRAINT [XPKPackage] PRIMARY KEY  CLUSTERED ([IDPackage] ASC)
go

CREATE TABLE [TransportOffer]
( 
	[IDTransportOffer]   integer  IDENTITY  NOT NULL ,
	[IDUser]             integer  NULL ,
	[IDPackage]          integer  NULL ,
	[Percentage]         decimal(10,3)  NULL 
)
go

ALTER TABLE [TransportOffer]
	ADD CONSTRAINT [XPKTransportOffer] PRIMARY KEY  CLUSTERED ([IDTransportOffer] ASC)
go

CREATE TABLE [User]
( 
	[Username]           varchar(100)  NOT NULL ,
	[LastName]           varchar(100)  NOT NULL 
	CONSTRAINT [StartsWithCapital_273224952]
		CHECK  ( /*TODO*/1=1 ),
	[PackagesSent]       integer  NULL ,
	[FirstName]          varchar(100)  NOT NULL 
	CONSTRAINT [StartsWithCapital_222030781]
		CHECK  ( /*TODO*/1=1 ),
	[IsAdmin]            bit  NULL ,
	[IDUser]             integer  IDENTITY  NOT NULL ,
	[Password]           varchar(100)  NOT NULL 
	CONSTRAINT [UserPasswordValid_368902668]
		CHECK  ( /*TODO*/1=1 )
)
go

ALTER TABLE [User]
	ADD CONSTRAINT [XPKUser] PRIMARY KEY  CLUSTERED ([IDUser] ASC)
go

ALTER TABLE [User]
	ADD CONSTRAINT [XAK1User] UNIQUE ([Username]  ASC)
go

CREATE TABLE [Vehicle]
( 
	[FuelType]           integer  NULL 
	CONSTRAINT [VehicleFuelTypeValues_1128505471]
		CHECK  ( [FuelType]=0 OR [FuelType]=1 OR [FuelType]=2 ),
	[FuelConsumption]    decimal(10,3)  NULL ,
	[PlateNumber]        varchar(100)  NOT NULL ,
	[IDVehicle]          integer  IDENTITY  NOT NULL 
)
go

ALTER TABLE [Vehicle]
	ADD CONSTRAINT [XPKVehicle] PRIMARY KEY  CLUSTERED ([IDVehicle] ASC)
go

ALTER TABLE [Vehicle]
	ADD CONSTRAINT [XAK1Vehicle] UNIQUE ([PlateNumber]  ASC)
go


ALTER TABLE [Courier]
	ADD CONSTRAINT [R_2] FOREIGN KEY ([IDUser]) REFERENCES [User]([IDUser])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go

ALTER TABLE [Courier]
	ADD CONSTRAINT [R_3] FOREIGN KEY ([IDVehicle]) REFERENCES [Vehicle]([IDVehicle])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go


ALTER TABLE [CourierRequest]
	ADD CONSTRAINT [R_11] FOREIGN KEY ([IDUser]) REFERENCES [User]([IDUser])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go

ALTER TABLE [CourierRequest]
	ADD CONSTRAINT [R_12] FOREIGN KEY ([IDVehicle]) REFERENCES [Vehicle]([IDVehicle])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go


ALTER TABLE [District]
	ADD CONSTRAINT [R_1] FOREIGN KEY ([IDCity]) REFERENCES [City]([IDCity])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go


ALTER TABLE [Drive]
	ADD CONSTRAINT [R_19] FOREIGN KEY ([IDUser]) REFERENCES [Courier]([IDUser])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Drive]
	ADD CONSTRAINT [R_20] FOREIGN KEY ([IDPackage]) REFERENCES [Package]([IDPackage])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Package]
	ADD CONSTRAINT [R_13] FOREIGN KEY ([IDDistrict1]) REFERENCES [District]([IDDistrict])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go

ALTER TABLE [Package]
	ADD CONSTRAINT [R_15] FOREIGN KEY ([IDDistrict2]) REFERENCES [District]([IDDistrict])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Package]
	ADD CONSTRAINT [R_16] FOREIGN KEY ([IDUser]) REFERENCES [User]([IDUser])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go


ALTER TABLE [TransportOffer]
	ADD CONSTRAINT [R_17] FOREIGN KEY ([IDUser]) REFERENCES [Courier]([IDUser])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go

ALTER TABLE [TransportOffer]
	ADD CONSTRAINT [R_18] FOREIGN KEY ([IDPackage]) REFERENCES [Package]([IDPackage])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go
