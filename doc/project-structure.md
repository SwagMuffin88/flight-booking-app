## Structure

This project contains both a Spring Boot backend and a React frontend. Below is an overview of the structure:

````
flight-booking-app/
├── src/                                    # Spring Boot backend source code 
│   ├── main/
│   │   ├── java/com/cgi/flightbookingapp/
│   │   │   ├── config/                     # Spring configuration files (e.g. CORS, DataSeeder)
│   │   │   ├── controller/                 # REST API controllers
│   │   │   ├── DTO/                        # Data Transfer Objects for API responses
│   │   │   ├── exception/                  # Custom exceptions
│   │   │   ├── model/                      # JPA entity classes
│   │   │   ├── repository/                 # JPA repositories for data access
│   │   │   └── service/                    # Business logic and service layer
│   │   └── resources/
│   │       ├── application.yml             # Spring Boot configuration
│   │       └── setup.sql                   # Setup file for Postgres database
│   └── test/                               # Unit and integration tests
│
├── flight-booking-app-frontend/            # React frontend app
│   ├── public/                             # Static assets
│   ├── src/                                # React components, routes, and styling
│   │   ├── api/                            # AxiosConfig
│   │   ├── components/                     # Components for React application
│   ├── .gitignore
│   ├── package.json
│   └── README.md
│
├── doc/                                    # Documentation files
│   ├── openapi.yaml                        # OpenAPI YAML spec
│   ├── project-structure.md                # (This file)
│   └── setup-guide.md                      # App setup guide
│
├── .gitignore
├── .gitattributes
├── mvnw / mvnw.cmd                         # Maven wrapper scripts
├── pom.xml                                 # Backend dependencies and build config
└── README.md                               # Project overview and usage
