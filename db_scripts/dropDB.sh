#!/bin/sh

psql -d postgres -h localhost -p 5432 -U postgres -f drop_database_v1.sql
