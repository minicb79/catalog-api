--DROP TABLE IF EXISTS `ITEM`;
DROP TABLE IF EXISTS `library`;

CREATE TABLE IF NOT EXISTS `library`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `description` VARCHAR(160)
);

-- CREATE TABLE IF NOT EXISTS `ITEM`(
--     `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
--     `title` VARCHAR(60) NOT NULL ,
--     `subtitle` VARCHAR(120),
--     `barcode` VARCHAR(20),
--     `type` VARCHAR(20),
-- --     `parent_item_id` INTEGER NULL,
--     `library_id` INTEGER NOT NULL
-- );

-- ALTER TABLE `ITEM` ADD FOREIGN KEY ( `library_id` ) REFERENCES `LIBRARY`(`id`);

-- ALTER TABLE `item` ADD FOREIGN KEY ( `id` ) REFERENCES `item`(`id`);