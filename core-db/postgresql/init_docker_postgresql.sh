#!/bin/bash

# this script is runned when the docker container is built
# it imports the base database structure and create the database for the tests

DATABASE_PASSWORD="teste123"
DATABASE_USER="rmcdbuser"
DATABASE_NAME="rmcdb"
DATABASE_SCHEMA="rmcsys"
DB_DUMP_LOCATION="/rmc-scripts/rmcdb-structure.sql"

echo "*** CREATING DATABASE ***"

# create default database
psql -U postgres <<EOF
\x
CREATE USER $DATABASE_USER WITH PASSWORD '$DATABASE_PASSWORD';
CREATE DATABASE $DATABASE_NAME OWNER $DATABASE_USER;
GRANT ALL PRIVILEGES ON DATABASE $DATABASE_NAME TO postgres;
\connect $DATABASE_NAME;
create schema $DATABASE_SCHEMA authorization $DATABASE_USER;
EOF

# clean sql_dump - because I want to have a one-line command

# remove indentation
#sed "s/^[ \t]*//" -i "$DB_DUMP_LOCATION"

# remove comments
#sed '/^--/ d' -i "$DB_DUMP_LOCATION"

# remove new lines
#sed ':a;N;$!ba;s/\n/ /g' -i "$DB_DUMP_LOCATION"

# remove other spaces
#sed 's/  */ /g' -i "$DB_DUMP_LOCATION"

# remove firsts line spaces
#sed 's/^ *//' -i "$DB_DUMP_LOCATION"

# append new line at the end (suggested by @Nicola Ferraro)
#sed -e '$a\' -i "$DB_DUMP_LOCATION"

# import sql_dump
# gosu postgres postgres --single "$DATABASE_NAME" < "$DB_DUMP_LOCATION";
# psql -U postgres < "$DB_DUMP_LOCATION"
psql -d $DATABASE_NAME -U $DATABASE_USER < "$DB_DUMP_LOCATION"


echo "*** DATABASE CREATED! ***"
