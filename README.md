# Ticket Booking Microservices

> **Status:** Currently under development

This repository contains a microservices-based ticket booking system, consisting of two main services:

- **Booking Service**: Handles ticket booking requests.
- **Inventory Service**: Manages event and venue inventory.

---

## Project Structure

```
.
├── bookingservice/
│   ├── src/
│   ├── pom.xml
│   └── ...
├── inventoryservice/
│   ├── src/
│   ├── docker/
│   ├── docker-compose.yml
│   ├── pom.xml
│   └── ...
└── README.md
```

---

## Services Overview

### Booking Service

- **Path:** [`bookingservice/`](bookingservice/)
- **Description:** Handles ticket booking requests.
- **Main entry:** [`com.ticketbooking.bookingservice.BookingserviceApplication`](bookingservice/src/main/java/com/ticketbooking/bookingservice/BookingserviceApplication.java)
- **API Endpoint:**  
  - `POST /api/v1/booking`  
    Request:  
    ```json
    {
      "userId": 1,
      "eventId": 2,
      "ticketCount": 3
    }
    ```
    Response:  
    See [`BookingResponse`](bookingservice/src/main/java/com/ticketbooking/bookingservice/response/BookingResponse.java)

### Inventory Service

- **Path:** [`inventoryservice/`](inventoryservice/)
- **Description:** Manages venues and events, including their capacities.
- **Main entry:** [`com.example.ticketbooking.inventoryservice.InventoryserviceApplication`](inventoryservice/src/main/java/com/example/ticketbooking/inventoryservice/InventoryserviceApplication.java)
- **API Endpoints:**  
  - `GET /api/v1/inventory/events`  
    Returns all events with their capacities and venues.  
    See [`EventInventoryResponse`](inventoryservice/src/main/java/com/example/ticketbooking/inventoryservice/response/EventInventoryResponse.java)
  - `GET /api/v1/inventory/venue/{venueId}`  
    Returns information about a specific venue.  
    See [`VenueInventoryResponse`](inventoryservice/src/main/java/com/example/ticketbooking/inventoryservice/response/VenueInventoryResponse.java)
- **Database:** MySQL (see [`docker-compose.yml`](inventoryservice/docker-compose.yml))

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- Docker (for running MySQL locally)

### Setup Steps

1. **Start MySQL Database**

   From the `inventoryservice` directory, run:

   ```sh
   docker-compose up -d
   ```

2. **Run Inventory Service**

   ```sh
   cd inventoryservice
   ./mvnw spring-boot:run
   ```

3. **Run Booking Service**

   ```sh
   cd bookingservice
   ./mvnw spring-boot:run
   ```

---

## Database

- **Schema migration:** Handled by Flyway ([`V1__init.sql`](inventoryservice/src/main/resources/db/migration/V1__init.sql))
- **Initial schema:** See [`init.sql`](inventoryservice/docker/mysql/init.sql)

---

## Development

- Both services use Spring Boot and Lombok.
- Inventory service uses Flyway for DB migrations.
