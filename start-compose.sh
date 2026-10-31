#!/bin/bash

set -e

echo "Starting all microservices..."

docker compose build

docker compose up -d

echo "Current container status:"

docker compose ps

echo "All microservices started successfully!"