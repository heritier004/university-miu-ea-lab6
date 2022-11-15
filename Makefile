all: network db_image prod_db prod_image product users_db users_image users

network:
	docker network create lab6

db_image:
	docker pull postgress

prod_db:
	docker run --name prod-db-server -d --rm -it -e POSTGRES_PASSWORD=1234 -e POSTGRES_USERNAME=postgres -p 5433:5432 --network=lab6  postgres

prod_image:
	docker build -t product-service ./product

product:
	docker run --rm -d -it -p 8181:8080 --name product-service --network=lab6 docker.io/library/product-service

users_db:
	docker run --name users-db-server -d --rm -it -e POSTGRES_PASSWORD=1234 -e POSTGRES_USERNAME=postgres -p 5422:5432 --network=lab6  postgres

users_image:
	docker build -t users-service ./user

users:
	docker run --rm -d -it -p 8282:8080 --name users-service --network=lab6 docker.io/library/users-service
