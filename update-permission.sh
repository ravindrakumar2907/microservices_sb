# 1. Force Git to mark the files as executable
git update-index --chmod=+x stop-compose.sh
git update-index --chmod=+x start-compose.sh

# 2. Commit the permission change
git commit -m "Fix: Make shell scripts executable in Git tracking"

# 3. Push to your repository
git push
