DROP TABLE IF EXISTS `recipe`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `library`;

CREATE TABLE IF NOT EXISTS `library`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `description` VARCHAR(160)
);

CREATE TABLE IF NOT EXISTS `item`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(60) NOT NULL ,
    `subtitle` VARCHAR(120),
    `summary` VARCHAR(1024),
--     `parent_item_id` INTEGER NULL,
    `library_id` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `book`(
    `id` INTEGER PRIMARY KEY,
    `author` VARCHAR(60),
    `isbn` VARCHAR(20),
    `barcode` VARCHAR(20),
    `pages` integer
);

CREATE TABLE IF NOT EXISTS `recipe`(
    `id` INTEGER PRIMARY KEY,
    `meal` VARCHAR(30)
);

ALTER TABLE `item` ADD FOREIGN KEY ( `library_id` ) REFERENCES `library`(`id`);

-- ALTER TABLE `item` ADD FOREIGN KEY ( `id` ) REFERENCES `item`(`id`);