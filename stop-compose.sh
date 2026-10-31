#!/bin/bash
echo "🚀 Starting all microservices..."

# --build forces Docker to recreate images with your latest code updates
# -d runs the containers in the detached (background) mode
docker compose up -d --build

echo "📊 Current status of containers:"
docker compose ps
