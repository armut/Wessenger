CREATE TABLE Session
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR
);
CREATE TABLE session_history
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date DATETIME,
    message VARCHAR,
    user_id INTEGER,
    session_id INTEGER,
    CONSTRAINT session_history_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT session_history_Session_id_fk FOREIGN KEY (session_id) REFERENCES Session (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE session_user_lookup
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    session_id INTEGER,
    user_id INTEGER,
    CONSTRAINT " session_user_lookup_Session_id_fk" FOREIGN KEY (session_id) REFERENCES Session (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT " session_user_lookup_user_id_fk" FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE sqlite_master
(
    type text,
    name text,
    tbl_name text,
    rootpage integer,
    sql text
);
CREATE TABLE sqlite_sequence
(
    name ,
    seq 
);
CREATE TABLE user
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nick_name VARCHAR
);
CREATE UNIQUE INDEX user_nick_name_uindex ON user (nick_name);
CREATE TABLE user_group
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR
);
CREATE TABLE user_group_lookup
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_group_id INTEGER,
    user_id INTEGER,
    CONSTRAINT user_group_lookup_user_group_id_fk FOREIGN KEY (user_group_id) REFERENCES user_group (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT user_group_lookup_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
)
