INSERT INTO `library`(`name`, `description`) VALUES ('Library 1', 'Library Description 1');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 2', 'Library Description 2');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 3', 'Library Description 3');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 4', 'Library Description 4');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 5', 'Library Description 5');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 6', 'Library Description 6');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 7', 'Library Description 7');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 8', 'Library Description 8');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 9', 'Library Description 9');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 10', 'Library Description 10');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 11', 'Library Description 11');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 12', 'Library Description 12');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 13', 'Library Description 13');
INSERT INTO `library`(`name`, `description`) VALUES ('Library 14', 'Library Description 14');


INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 1', 'The 1st Subtitle', null, 1);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 2', null, null, 2);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 3', 'The 3rd Subtitle', 'This is a summary, for this item.', 2);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 4', null, '4th item summary. This is an amazing item', 3);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 5', 'The 5th Subtitle', null, 3);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 6', null, null, 3);
INSERT INTO `item`(`title`, `subtitle`, `summary`, `library_id`) VALUES ('Item 7', 'The 7th Subtitle', null, 7);

INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (1, 'Author 1', '12345678901234', '1111111111', 111);
INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (2, 'Author 2', '22345678901234', '2222222222', 2021);
INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (3, 'Author 3', '32345678901234', '3333333333', 313);
INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (4, 'Author 4', '42345678901234', '4444444444', 49);
INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (5, 'Author 5', '52345678901234', '5555555555', 588);
INSERT INTO `book`(`id`, `author`, `isbn`, `barcode`, `pages`) VALUES (7, 'Author 7', '72345678901234', '7777777777', 712);

INSERT INTO `recipe`(`id`, `meal`) VALUES (6, 'breakfast');
