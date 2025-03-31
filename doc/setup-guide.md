# Setup guide

### Prerequisites
Before setting up the project, make sure you have the following installed:
 - **Java 17+**
 - **PostgreSQL**
 - **Node.js** (v16+) & npm

You can check if you have these tools installed by running:

```bash
java -version
psql --version
node -v
npm -v
```

### Backend setup
1. Go to `/src/main/resources` and locate the `application-example.yml` ([Direct link](../src/main/resources/application-example.yml)).
2. Create a new `application.yml` under the same directory, copy the contents of `application-example.yml`.
3. In the file you created, update the fields in the file with credentials based on your local setup.
4. Open `setup.sql`, found in the same directory ([Direct link](../src/main/resources/setup.sql)).
5. Follow the prompts given by your IDE to configure Postgres connection.
6. Run the SQL script to create an empty database for this project.
7. With database and application properties set up, run the Spring Boot application.
8. You can verify existence of database entities by opening a new query console under `flight_db` and for example trying the query: `SELECT * FROM flight`.

### Frontend setup (React)
1. The easiest way to run the frontend would be by opening a new integrated terminal (like Powershell).
2. Make sure you are in the project root (under directory `.../flight-booking-app`).
3. `cd flight-booking-app-frontend`
4. `npm start`
5. The app should launch at http://localhost:3000 and communicate with the backend at http://localhost:8080.
