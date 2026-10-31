#!/bin/bash

set -e

echo "🚀 Starting all microservices..."

docker compose down

docker compose up -d --build

echo "📊 Current status of containers..."

docker compose ps

echo "✅ All microservices started successfully!"