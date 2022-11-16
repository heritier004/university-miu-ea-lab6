images: users_image prod_image cfg_server_image serv_discovery_image
svcs:  cfg_server prod_db users_db serv_discover
services: prod users

all: images svcs

network:
	docker network create lab6

db_image:
	docker pull postgress

prod_db:
	docker run --name prod-db-server -d --rm -it -e POSTGRES_PASSWORD=1234 -e POSTGRES_USERNAME=postgres -e POSTGRES_DB=prod-db -p 5433:5432 --network=lab6  postgres
prod_image:
	docker build -t product-service ./product
prod:
	docker run --rm -d -it -p 8181:8080 --name products-service --network=lab6 docker.io/library/product-service

users_db:
	docker run --name users-db-server -d --rm -it -e POSTGRES_PASSWORD=1234 -e POSTGRES_USERNAME=postgres -e POSTGRES_DB=users-db -p 5422:5432 --network=lab6  postgres
users_image:
	docker build -t users-service ./user
users:
	docker run --rm -d -it -p 8282:8080 --name users-service --network=lab6 docker.io/library/users-service

cfg_server_image:
	docker build -t config-server ./config-server
cfg_server:
	docker run --rm -d -it -p 8888:8888 --name config-server --network=lab6 docker.io/library/config-server

serv_discovery_image:
	docker build -t service-discovery ./service-discovery
serv_discover:
	docker run --rm -d -it -p 8761:8761 --name service-discovery --network=lab6 docker.io/library/service-discovery