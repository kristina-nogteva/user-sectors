DROP TABLE SECTOR;

CREATE TABLE SECTOR(ID INT NOT NULL,DESCRIPTION VARCHAR(50) NOT NULL, PARENT_SECTOR INT);

INSERT INTO SECTOR VALUES (1, 'Manufacturing', null);
INSERT INTO SECTOR VALUES (2, 'Service', null);
INSERT INTO SECTOR VALUES (3, 'Other', null);

INSERT INTO SECTOR VALUES (5, 'Printing', 1);
INSERT INTO SECTOR VALUES (6, 'Food and Beverage', 1);
INSERT INTO SECTOR VALUES (7, 'Textile and Clothing', 1);
INSERT INTO SECTOR VALUES (8, 'Wood', 1);
INSERT INTO SECTOR VALUES (9, 'Plastic and Rubber', 1);
INSERT INTO SECTOR VALUES (11, 'Metalworking', 1);
INSERT INTO SECTOR VALUES (12, 'Machinery', 1);
INSERT INTO SECTOR VALUES (13, 'Furniture', 1);
INSERT INTO SECTOR VALUES (18, 'Electronics and Optics', 1);
INSERT INTO SECTOR VALUES (19, 'Construction materials', 1);

INSERT INTO SECTOR VALUES (21, 'Transport and Logistics', 2);
INSERT INTO SECTOR VALUES (22, 'Tourism', 2);
INSERT INTO SECTOR VALUES (25, 'Business services', 2);
INSERT INTO SECTOR VALUES (28, 'Information Technology and Telecommunications', 2);
INSERT INTO SECTOR VALUES (35, 'Engineering', 2);
INSERT INTO SECTOR VALUES (141, 'Translation services', 2);

INSERT INTO SECTOR VALUES (29, 'Energy technology', 3);
INSERT INTO SECTOR VALUES (33, 'Environment', 3);
INSERT INTO SECTOR VALUES (37, 'Creative industries', 3);

INSERT INTO SECTOR VALUES (145, 'Labelling and packaging printing', 5);
INSERT INTO SECTOR VALUES (148, 'Advertising', 5);
INSERT INTO SECTOR VALUES (150, 'Book/Periodicals printing', 5);

INSERT INTO SECTOR VALUES (39, 'Milk & dairy products', 6);
INSERT INTO SECTOR VALUES (40, 'Meat & meat products', 6);
INSERT INTO SECTOR VALUES (42, 'Fish & fish products', 6);
INSERT INTO SECTOR VALUES (43, 'Beverages', 6);
INSERT INTO SECTOR VALUES (342, 'Bakery & confectionery products', 6);
INSERT INTO SECTOR VALUES (378, 'Sweets & snack food', 6);
INSERT INTO SECTOR VALUES (437, 'Other', 6);

INSERT INTO SECTOR VALUES (44, 'Clothing', 7);
INSERT INTO SECTOR VALUES (45, 'Textile', 7);

INSERT INTO SECTOR VALUES (47, 'Wooden houses', 8);
INSERT INTO SECTOR VALUES (51, 'Wooden building materials', 8);
INSERT INTO SECTOR VALUES (55, 'Other (Wood)', 8);

INSERT INTO SECTOR VALUES (54, 'Packaging', 9);
INSERT INTO SECTOR VALUES (556, 'Plastic goods', 9);
INSERT INTO SECTOR VALUES (559, 'Plastic processing technology', 9);
INSERT INTO SECTOR VALUES (560, 'Plastic profiles', 9);

INSERT INTO SECTOR VALUES (67, 'Construction of metal structures', 11);
INSERT INTO SECTOR VALUES (263, 'Houses and buildings', 11);
INSERT INTO SECTOR VALUES (267, 'Metal products', 11);
INSERT INTO SECTOR VALUES (542, 'Metal works', 11);

INSERT INTO SECTOR VALUES (91, 'Machinery equipment', 12);
INSERT INTO SECTOR VALUES (94, 'Machinery components', 12);
INSERT INTO SECTOR VALUES (97, 'Maritime', 12);
INSERT INTO SECTOR VALUES (93, 'Metal structures', 12);
INSERT INTO SECTOR VALUES (227, 'Repair and maintenance service', 12);
INSERT INTO SECTOR VALUES (508, 'Other', 12);

INSERT INTO SECTOR VALUES (98, 'Kitchen', 13);
INSERT INTO SECTOR VALUES (99, 'Project furniture', 13);
INSERT INTO SECTOR VALUES (101, 'Living room', 13);
INSERT INTO SECTOR VALUES (385, 'Bedroom', 13);
INSERT INTO SECTOR VALUES (389, 'Bathroom/sauna', 13);
INSERT INTO SECTOR VALUES (390, 'Children’s room', 13);
INSERT INTO SECTOR VALUES (392, 'Office', 13);
INSERT INTO SECTOR VALUES (394, 'Other (Furniture)', 13);
INSERT INTO SECTOR VALUES (341, 'Outdoor', 13);

INSERT INTO SECTOR VALUES (111, 'Air', 21);
INSERT INTO SECTOR VALUES (112, 'Road', 21);
INSERT INTO SECTOR VALUES (113, 'Water', 21);
INSERT INTO SECTOR VALUES (114, 'Rail', 21);

INSERT INTO SECTOR VALUES (121, 'Software, Hardware', 28);
INSERT INTO SECTOR VALUES (122, 'Telecommunications', 28);
INSERT INTO SECTOR VALUES (576, 'Programming, Consultancy', 28);
INSERT INTO SECTOR VALUES (581, 'Data processing, Web portals, E-marketing', 28);

INSERT INTO SECTOR VALUES (271, 'Aluminium and steel workboats', 97);
INSERT INTO SECTOR VALUES (269, 'Boat/Yacht building', 97);
INSERT INTO SECTOR VALUES (230, 'Ship repair and conversion', 97);

INSERT INTO SECTOR VALUES (62, 'Forgings, Fasteners', 542);
INSERT INTO SECTOR VALUES (66, 'MIG, TIG, Aluminum welding', 542);
INSERT INTO SECTOR VALUES (69, 'Gas, Plasma, Laser cutting', 542);
INSERT INTO SECTOR VALUES (75, 'CNC-machining', 542);

INSERT INTO SECTOR VALUES (55, 'Blowing', 559);
INSERT INTO SECTOR VALUES (57, 'Moulding', 559);
INSERT INTO SECTOR VALUES (53, 'Plastics welding and processing', 559);