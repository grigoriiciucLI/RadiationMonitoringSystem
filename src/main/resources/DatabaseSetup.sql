CREATE TABLE IF NOT EXISTS monitoringstations (
    id SERIAL PRIMARY KEY,
    location VARCHAR(150) NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    established_date DATE NULL,
    operator VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS radiationreadings (
    id SERIAL PRIMARY KEY,
    station_id INT  NOT NULL REFERENCES monitoringstations(id) ON DELETE CASCADE,
    timestamp TIMESTAMP NOT NULL DEFAULT NOW(),
    radiation_level DECIMAL(10,4) NOT NULL CHECK (radiation_level >= 0),
    radiation_type VARCHAR(20) NOT NULL,
    alert_status VARCHAR(20) NOT NULL,
    notes VARCHAR(500) NULL
    );

CREATE TABLE IF NOT EXISTS operators (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NULL
    );

CREATE TABLE IF NOT EXISTS stationoperatorassignments (
    station_id INT NOT NULL REFERENCES monitoringstations(id) ON DELETE CASCADE,
    operator_id INT NOT NULL REFERENCES operators(id) ON DELETE CASCADE,
    assigned_on DATE NULL,
    PRIMARY KEY (station_id, operator_id)
    );

INSERT INTO monitoringstations (location, type, status, established_date, operator) VALUES
('Bucharest Central',    'Fixed',     'Active',      '2010-03-15', 'ANDR Romania'),
('Cluj-Napoca North',    'Fixed',     'Active',      '2012-07-22', 'ANDR Romania'),
('Constanta Coastal',    'Mobile',    'Active',      '2015-11-01', 'ANDR Romania'),
('Timisoara Industrial', 'Fixed',     'Maintenance', '2009-05-30', 'Local Authority'),
('Iasi University Zone', 'Emergency', 'Inactive',    '2018-02-14', 'University Research');

INSERT INTO radiationreadings (station_id, timestamp, radiation_level, radiation_type, alert_status, notes) VALUES
(1, '2024-01-10 08:00:00', 0.0021, 'Gamma', 'Normal',   'Routine morning reading'),
(1, '2024-01-10 14:00:00', 0.0023, 'Gamma', 'Normal',   'Afternoon reading – normal range'),
(1, '2024-01-11 08:00:00', 0.1850, 'Beta',  'Warning',  'Elevated – check instrument calibration'),
(1, '2024-01-12 09:30:00', 0.0019, 'Gamma', 'Normal',   'Back to baseline after maintenance'),
(1, '2024-02-01 07:45:00', 0.0022, 'Alpha', 'Normal',   NULL),
(2, '2024-01-15 10:00:00', 0.0031, 'Gamma', 'Normal',   'Regular check'),
(2, '2024-01-20 11:30:00', 0.4500, 'Gamma', 'Warning',  'Unusual spike – verified with backup sensor'),
(2, '2024-01-21 08:00:00', 0.0028, 'Gamma', 'Normal',   'Spike resolved'),
(3, '2024-02-05 09:00:00', 0.0015, 'Alpha', 'Normal',   'Coastal baseline'),
(3, '2024-02-06 15:00:00', 1.2000, 'Beta',  'Critical', 'CRITICAL – emergency team notified'),
(3, '2024-02-07 08:00:00', 0.0017, 'Alpha', 'Normal',   'Level returned to normal after incident review'),
(4, '2024-03-01 07:00:00', 0.0055, 'Beta',  'Normal',   'Industrial background slightly higher than average'),
(4, '2024-03-15 12:00:00', 0.0060, 'Beta',  'Normal',   NULL),
(5, '2024-04-01 09:00:00', 0.0010, 'Gamma', 'Normal',   'Research lab vicinity – normal');
INSERT INTO operators (name, email) VALUES
                                        ('Ion Popescu',     'ion.popescu@andr.ro'),
                                        ('Maria Ionescu',   'maria.ionescu@andr.ro'),
                                        ('Alex Dumitrescu', 'alex.d@local.ro');

INSERT INTO stationoperatorassignments (station_id, operator_id, assigned_on) VALUES
                                                                                    (1, 1, '2023-01-01'),
                                                                                    (1, 2, '2023-06-15'),
                                                                                    (2, 2, '2022-09-01'),
                                                                                    (3, 3, '2021-04-10'),
                                                                                    (4, 1, '2020-11-01');
