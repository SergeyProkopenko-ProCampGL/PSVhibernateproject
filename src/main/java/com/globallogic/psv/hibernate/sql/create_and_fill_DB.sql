DROP SCHEMA IF EXISTS procamp_psv;
CREATE SCHEMA procamp_psv;
USE procamp_psv;

CREATE TABLE user (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL UNIQUE,
  `email_backup` varchar(45) DEFAULT NULL,
  `TN` varchar(20) NOT NULL UNIQUE,
  `TN_backup` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO user (`first_name`,   `last_name`,   `email`,   `email_backup`,   `TN`,   `TN_backup`)
VALUES ('Ivan', 'Petrenko', 'petrenko@ukr.net', NULL, '380501111111', NULL),
		('Petro', 'Ivanenko', 'ivaenko@i.ua', NULL, '380502222222', NULL),
		('Anton', 'Tkachuk', 'tkachuk@meta.ua', NULL, '380673333333', NULL),
		('Mykola', 'Salo', 'salo@gmail.ua', NULL, '380684444444', NULL),
		('Stepan', 'Avramenko', 'avramenko@yahoo.com', NULL, '380995555555', NULL);

CREATE TABLE report (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `report_name` varchar(45) DEFAULT NULL,
  `price`  DECIMAL (12,2) DEFAULT NULL,
  `report_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
 CONSTRAINT `report_user_id_fk` FOREIGN KEY report(user_id) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO report (`user_id`,   `report_name`,   `price`,   `report_date`)
VALUES ('1', 'Petrenko Report1', '0.0', '2020-11-20 10:01:00.1'),
		('1', 'Petrenko Report2', '0.0', '2020-11-25 10:01:00'),
		('2', 'Ivanenko Report1', '0.0', NOW()),
		('2', 'Ivanenko Report2', '0.0', '2020-11-22 13:00:00'),
		('3', 'Tkachuk Report1', '0.0', NOW()),
		('3', 'Tkachuk Report2', '0.0', '2020-11-27 14:00:00'),
		('5', 'Avramenko Report1', '0.0', NOW()),
		('5', 'Avramenko Report2', '0.0', '2020-11-28 15:00:00');

CREATE TABLE building (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` int(11) NOT NULL,
  `building_name` varchar(45) NOT NULL UNIQUE,
  `is_active` BOOL NOT NULL,
  PRIMARY KEY (`id`),
 CONSTRAINT `building_report_id_fk` FOREIGN KEY report(report_id) references report(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO building (`report_id`,   `building_name`, `is_active`)
VALUES ('1', 'Troeschina 365', '1'),
		('1', 'Troeschina 360', '1'),
		('2', 'Pechersk 15', '1'),
		('2', 'Pechersk 20', '1'),
		('3', 'Obolon 25', '1'),
		('3', 'Obolon 30', '1'),
		('4', 'Bortnychi-1', '1'),
		('4', 'Bortnychi-2', '0'),
    ('5', 'Nyvky 5', '1'),
		('5', 'Nyvky 10', '1'),
		('6', 'Brovary 35', '1'),
		('6', 'Brovary 40', '1'),
		('7', 'Feofaniya 50', '1'),
		('7', 'Feofaniya 60', '0'),
		('8', 'Koncha Zaspa 100', '1'),
		('8', 'Koncha Zaspa 200', '0');

CREATE TABLE activity (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_id` int(11) NOT NULL,
  `work_name` varchar(45) NOT NULL UNIQUE,
  `price` DECIMAL (10,2),
  `amount` DECIMAL (10,2),
  PRIMARY KEY (`id`),
 CONSTRAINT `activity_building_id_fk` FOREIGN KEY activity(building_id)
  references building(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO activity (`building_id`,   `work_name`, `price`, `amount`)
VALUES ('1', 'Interior work', '100', '15.1' ),
		('1', 'Ceiling finishing work', '200', '7.1'),
		('2', 'Wallpapering', '50', '35.5'),
		('2', 'Interior room finishing work', '80', '25.5'),
		('3', 'Floor finishing work', '150', '15.4'),
		('3', 'Tatami work', '250', '6.1'),
		('4', 'Fusuma work', '230', '8.5'),
		('4', 'Furniture work', '180', '5.5'),
    ('5', 'Soundproofing', '150', '20.3'),
		('5', 'Interior fire hydrant installation', '170', '3.0'),
		('6', 'Sprinkler installation', '50', '10.0'),
		('6', 'Movable fire fighting pump installation', '1500', '1.0'),
		('7', 'Fire alarm facilities work', '2000', '1.0'),
		('7', 'Installation of metal evacuation ladders', '3000', '1.0'),
		('8', 'Installation of escape chutes', '500', '5'),
		('8', 'Installation of evacuation bridges', '5000', '1.0'),
		('9', 'Plasterwork', '70', '35.5' ),
		('9', 'Mortar work', '30', '40.0'),
		('10', 'Concrete waterproofing', '20', '40.5'),
		('10', 'Spray work', '30', '50.5'),
		('11', 'Grinding work', '180', '25.3'),
		('11', 'Washing out', '15', '60.5'),
		('12', 'Pipe rehabilitation work', '230', '15.5'),
		('12', 'Cooler installation', '500', '2.0'),
    ('13', 'Heater installation', '350', '5.0'),
		('13', 'Refrigerator installation', '600', '2.0'),
		('14', 'Concrete block masonry', '25', '100.0'),
		('14', 'Brick masonry', '10', '500.0'),
		('15', 'Dredging work', '20000', '1.0'),
		('15', 'Plant installation', '250', '5.0'),
		('16', 'injection well work', '10000', '1.0'),
		('16', 'Antenna installation', '2000', '1.0');






