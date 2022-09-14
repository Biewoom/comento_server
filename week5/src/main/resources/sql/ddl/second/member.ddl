create table MEMBER
(
    MEMBER_ID BIGINT auto_increment
        primary key,
    USERNAME  CHARACTER VARYING(255),
    LOCKER_ID BIGINT,
    TEAM_ID   BIGINT,
    constraint TEAM_FOREIGN
        foreign key (TEAM_ID) references TEAM,
    CONSTRAINT LOCKER_FOREIGN
        foreign key (LOCKER_ID) references LOCKER
);