USE [si4sab];
GO

IF OBJECT_ID('GrantRequest', 'P') IS NOT NULL  
    DROP PROCEDURE [GrantRequest];
GO

CREATE PROCEDURE [GrantRequest]
	@username VARCHAR(100),
	@success BIT OUTPUT
AS
BEGIN
	DECLARE @iduser INT, @idvehicle INT;

	SELECT @iduser = [IDUser] FROM [User] WHERE [Username] = @username;

	SELECT @idvehicle = v.[IDVehicle]
	FROM [Vehicle] v JOIN [CourierRequest] cr ON v.[IDVehicle] = cr.[IDVehicle]
	WHERE cr.[IDUser] = @iduser;

	IF EXISTS(SELECT * FROM [Courier] WHERE [IDVehicle] = @idvehicle)
	BEGIN
		SET @success = 0;
		RETURN;
	END;

	INSERT INTO [Courier]([IDUser], [IDVehicle], [Status], [Deliveries], [Profit])
	VALUES (@iduser, @idvehicle, 0, 0, 0);

	DELETE FROM [CourierRequest]
	WHERE [IDUser] = @iduser;

	SET @success = 1;
END;
GO
