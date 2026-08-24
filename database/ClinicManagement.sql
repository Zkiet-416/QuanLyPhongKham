use clinic_management;
GO

-- 1. Specialties
create table specialties (
	id INT IDENTITY(1,1) primary key,
	name NVARCHAR(100) NOT NULL,
	description NVARCHAR(500)
);
GO

-- 2. Doctors
create table doctors (
	id INT IDENTITY(1,1) primary key,
	full_name NVARCHAR(100) NOT NULL,
	phone VARCHAR(10),
	email VARCHAR(100),
	specialty_id INT NOT NULL,

	CONSTRAINT FK_doctors_specialties
		FOREIGN KEY (specialty_id)
		REFERENCES specialties(id)
);
GO

-- 3. Patients
create table patients (
	id INT IDENTITY(1,1) primary key,
	full_name NVARCHAR(100) NOT NULL,
	date_of_birth DATE,
	gender NVARCHAR(10),
	phone VARCHAR(10),
	email NVARCHAR(100),
	address NVARCHAR(300),
);
GO

-- 4. Appointments
create table appointments (
	id INT IDENTITY(1,1) primary key,
	patient_id INT NOT NULL,
	doctor_id INT NOT NULL,
	appointment_date DATETIME2 NOT NULL,
	reason NVARCHAR(500),
	status NVARCHAR(30) NOT NULL DEFAULT N'PENDING',

	CONSTRAINT FK_appointments_patients
		FOREIGN KEY (patient_id)
		REFERENCES patients(id),

	CONSTRAINT FK_appointments_doctors
		FOREIGN KEY (doctor_id)
		REFERENCES doctors(id),
);
GO

-- 5. MedicalRecords
create table MedicalRecords (
	id INT IDENTITY(1,1) primary key,
	appointment_id INT NOT NULL,
	diagnosis NVARCHAR(500),
	symptoms NVARCHAR(1000),
	treatment NVARCHAR(1000),
	notes NVARCHAR(1000),

	CONSTRAINT FK_MedicalRecords_appointments
		FOREIGN KEY (appointment_id)
        REFERENCES appointments(id)
);
GO

-- 6. Medicines
create table medicines (
	id INT IDENTITY(1,1) primary key,
	name NVARCHAR(100) NOT NULL,
	unit VARCHAR(50) NOT NULL,
	price DECIMAL(12,2) NOT NULL DEFAULT 0,
	quantity INT NOT NULL DEFAULT 0,

	CONSTRAINT CK_medicines_price
		CHECK (price >= 0),

	CONSTRAINT CK_medicines_quantity
		CHECK (quantity >= 0)
);
GO

-- 7. Prescriptions
create table prescriptions (
	id INT IDENTITY(1,1) primary key,
	medical_record_id INT NOT NULL,
	medicine_id INT NOT NULL,
	quantity INT NOT NULL,
	dosage NVARCHAR(250),
	instructions NVARCHAR(500),

	CONSTRAINT FK_prescriptions_MedicalRecords
		FOREIGN KEY (medical_record_id)
		REFERENCES MedicalRecords(id),

	CONSTRAINT FK_prescriptions_medicines
		FOREIGN KEY (medicine_id)
		REFERENCES medicines(id),

	CONSTRAINT CK_prescriptions_quantity
		CHECK (quantity >= 0),
);
GO