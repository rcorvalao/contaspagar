FROM postgres:9.6

MAINTAINER Rafael Corvalao <rafael.corvalao@gmail.com>

RUN mkdir /rmc-scripts


# ENV ACCESS_TOKEN abc
# ENV SECRET_TOKEN xyz

COPY rmcdb-structure.sql /rmc-scripts/

COPY init_docker_postgresql.sh /docker-entrypoint-initdb.d/


# ENTRYPOINT [“/app/elasticpush”]
