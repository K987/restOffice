#!/bin/sh

psql -d postgres -h localhost -p 5432 -U postgres -f create_schema_v1.sql
psql -d restofficedb -h localhost -p 5432 -U postgres -f create_table_v1.sql
psql -d restofficedb -h localhost -p 5432 -U postgres -f load_from_csv.sql
psql -d restofficedb -h localhost -p 5432 -U postgres -f grant_access_v1.sql
