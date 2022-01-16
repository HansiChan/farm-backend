create database farm;
use farm;
create table land_nft
(
    token_id    int,
    rarity      varchar(300),
    capacity    int,
    create_time bigint,
    PRIMARY KEY (`token_id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

create table pond_nft
(
    token_id    int,
    rarity      varchar(300),
    capacity    int,
    create_time bigint,
    PRIMARY KEY (`token_id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

create table land_transaction
(
    token_id         int,
    transaction_hash varchar(300),
    transaction_time bigint,
    buyer            varchar(300),
    price            double,
    sell_round       int,
    PRIMARY KEY (`token_id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

create table pond_transaction
(
    token_id         int,
    transaction_hash varchar(300),
    transaction_time bigint,
    buyer            varchar(300),
    price            double,
    sell_round       int,
    PRIMARY KEY (`token_id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;