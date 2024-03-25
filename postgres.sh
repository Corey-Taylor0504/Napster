docker run -d --name postgres-container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=musicdb -p 5432:5432 postgres
