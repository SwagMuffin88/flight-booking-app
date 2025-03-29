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
│   │   │   ├── model/                      # JPA entity classes
│   │   │   └── service/                    # Business logic and service layer
│   │   └── resources/
│   │       ├── application.yml             # Spring Boot configuration
│   │       └── openapi.yml                 # OpenAPI documentation
│   └── test/                               # Unit and integration tests
│
├── flight-booking-app-frontend/            # React frontend app
│   ├── public/                             # Static assets
│   ├── src/                                # React components, routes, and styling
│   ├── .gitignore
│   ├── package.json
│   └── README.md
│
├── .gitignore
├── .gitattributes
├── mvnw / mvnw.cmd                         # Maven wrapper scripts
├── pom.xml                                 # Backend dependencies and build config
└── README.md                               # Project overview and usage
