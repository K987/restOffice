-- create database and access
CREATE DATABASE restofficedb encoding 'UTF-8' LC_COLLATE='hu_HU.UTF-8' LC_CTYPE='hu_HU.UTF-8' TEMPLATE=template0;
CREATE ROLE ro_crud_role WITH NOSUPERUSER NOCREATEDB NOCREATEROLE;
CREATE USER ro_crud_user;
ALTER USER ro_crud_user PASSWORD 'pwd123';
GRANT ro_crud_role TO ro_crud_user;


--locale=en_US.utf8
