drop table if exists ticket CASCADE;
drop table if exists passenger CASCADE;
drop table if exists client CASCADE;
drop table if exists seat CASCADE;
drop table if exists flight CASCADE;
drop table if exists plane CASCADE;
drop table if exists air_company CASCADE;

CREATE TABLE  IF NOT EXISTS  client (
    id bigserial PRIMARY KEY,
    first_name character varying(255) not null,
    second_name character varying(255) not null,
    phone_number int DEFAULT 0
    );

CREATE TABLE  IF NOT EXISTS air_company(
    id bigserial PRIMARY KEY,
    name_company character varying(255) NOT NULL,
    phone_number int DEFAULT 0
    );

CREATE TABLE  IF NOT EXISTS plane(
    id bigserial PRIMARY KEY,
    invertor_number integer default 0,
    name_plane character varying(255) default null,
    name_pilot character varying(255) default null,
    quantity_seats integer default 0,
    seats_in_line integer default 0,
    quantity_lines integer default 0,
    company_id bigint,
    FOREIGN KEY (company_id) REFERENCES air_company(id)
    );

CREATE TABLE  IF NOT EXISTS flight(
    id bigserial PRIMARY KEY,
    number_flight character varying(255) default null,
    arrive_city character varying(255) default null,
    arrive_date_time timestamp,
    duration_flight integer default 0,
    departure_city character varying(255) default null,
    departure_date_time timestamp,
    plane_id bigint,
    FOREIGN KEY (plane_id) REFERENCES plane(id)
    );

CREATE TABLE  IF NOT EXISTS seat(
    id bigserial PRIMARY KEY,
    booked boolean default false,
     number_seat character varying(255),
    flight_id bigint,
    FOREIGN KEY (flight_id) REFERENCES flight(id)
    );

CREATE TABLE  IF NOT EXISTS ticket(
    id bigserial PRIMARY KEY,
    booked_date_time timestamp,
    first_name_passenger character varying(255),
    second_name_passenger character varying(255),
    phone_number_passenger integer default 0,
    passport_number_passenger character varying(255),
    seat_id bigint,
    client_id bigint,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (seat_id) REFERENCES seat(id)
    );

CREATE TABLE  IF NOT EXISTS PASSENGER(
    id bigserial PRIMARY KEY,
    first_name character varying(255) not null,
    second_name character varying(255) not null,
    passport_number character varying(255) not null,
    phone_number integer DEFAULT 0,
    client_id bigint,
    FOREIGN KEY (client_id) REFERENCES client(id)
    );

INSERT INTO air_company(name_company, phone_number) values('BELAVIA',  2232323);
INSERT INTO air_company(name_company, phone_number) values('AEROFLOT',  3234455);

INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(234567, 'AH 24', 'Nikita', 48, 4, 12, 1);
INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(1122423, 'AH 24', 'Ruslan', 48, 4, 12, 1);
INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(7777771, 'AH 24', 'Tereshkov', 48, 4, 12, 1);
INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(555555, 'AH 24', 'Peskov', 48, 4, 12, 2);
INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(333333, 'AH 24', 'Zhyrynovski', 48, 4, 12, 2);
INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
values(111111, 'AH 24', 'Mishustin', 48, 4, 12, 2);

INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK256', 'BREST', '2021-12-15 14:00:00', 50, 'GOMEL', '2021-12-15 14:50:00', 1);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK333', 'BREST', '2021-12-15 14:00:00', 45, 'MINSK', '2021-12-15 14:45:00', 2);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK123', 'MINSK', '2021-12-15 16:00:00', 60, 'GOMEL', '2021-12-15 17:00:00', 3);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK777', 'BREST', '2021-12-15 19:00:00', 80, 'GRODNO', '2021-12-15 20:20:00', 4);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK999', 'BREST', '2021-12-15 10:00:00', 80, 'MINSK', '2021-12-15 11:00:00', 5);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK567', 'MINSK', '2021-12-16 20:00:00', 80, 'MOGILEV', '2021-12-16 21:20:00', 6);
INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
values('FK171', 'GRODNO', '2021-12-16 10:00:00', 80, 'GOMEL', '2021-12-16 11:10:00', 1);


INSERT INTO client (first_name, second_name,  phone_number) VALUES ('sasha', 'sakahruk',  292001211);
INSERT INTO client (first_name, second_name,  phone_number) VALUES ('petia', 'olejnik', 292112121);
INSERT INTO client (first_name, second_name,  phone_number) VALUES ('sasha', 'kazak', 297330135);
INSERT INTO client (first_name, second_name,  phone_number) VALUES ('roma', 'salapura', 297229238);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 1);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 1);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '7B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 2);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 2);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '7B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 3);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 3);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '4D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '7B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 4);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 4);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 5);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 5);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 6);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 6);

INSERT INTO seat(booked, number_seat, flight_id) values(true, '1A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '1C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '1D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '2C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '2D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '3C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '3D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '4D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '5C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '5D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '6C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '6D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '7D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '8C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '8D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '9C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '9D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '10C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '10D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '11D', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12A', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12B', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(true, '12C', 7);
INSERT INTO seat(booked, number_seat, flight_id) values(false, '12D', 7);

INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Vadim', 'Kazak', 'AB3234455', 297903271, 3);
INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Maksim', 'Fedeev', 'AB3457901', 296330195, 3);
INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Semen', 'Dron', 'AB6540394', 292250081, 3);
INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Ruslan', 'Zavizenec', 'AB3840101', 295643201, 4);
INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Zaur', 'Martirosian', 'AB3110401', 291230673, 4);
INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, client_id)
values('Igor', 'Olechovich', 'AB5349591', 296446666, 4);

INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time,   seat_id, client_id)
values('Vadim', 'Kazak', 'AB 3234455', 297903271, '2021-11-01 14:50:00', 1, 3);
INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time, seat_id, client_id)
values('Maksim', 'Fedeev', 'AB 3457901', 296330195, '2021-11-01 14:50:00', 58, 3);
INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time, seat_id, client_id)
values('Semen', 'Dron', 'AB 6540394', 292250081, '2021-11-01 14:50:00', 104, 3);
INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time, seat_id, client_id)
values('Ruslan', 'Zavizenec', 'AB 3840101', 295643201, '2021-11-01 14:50:00', 156, 4);
INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time, seat_id, client_id)
values('Zaur', 'Martirosian', 'AB 3110401', 291230673, '2021-11-01 14:50:00', 179 ,4);
INSERT INTO ticket(first_name_passenger, second_name_passenger, passport_number_passenger, phone_number_passenger, booked_date_time, seat_id, client_id)
values('Igor', 'Olechovich', 'AB 5349591', 296446666, '2021-11-01 14:50:00', 203, 4);
