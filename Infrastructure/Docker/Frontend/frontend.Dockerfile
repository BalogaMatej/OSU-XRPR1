# --- STAGE 1: Build the React/Vite Application ---
FROM node:20-alpine AS builder

# Set the working directory INSIDE the container to where the app will live.
# This path is relative to the container's root, NOT the host file system.
WORKDIR /app

# Copy package.json and lock file from the host's build context 
# into the container's working directory (/app).
# The path "Frontend/my-react-app/" is relative to YourProjectRoot (the context).
COPY Frontend/my-react-app/package.json ./
COPY Frontend/my-react-app/package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application source code
COPY Frontend/my-react-app/ .

# Run the Vite build command (creates the 'dist' folder in /app)
RUN npm run build

# --- STAGE 2: Serve the Static Files with Nginx ---
FROM nginx:alpine

# Copy the custom Nginx config from the infrastructure folder
# Assuming your nginx.conf is at Infrastructure/Frontend/nginx.conf
COPY Infrastructure/Docker/Frontend/nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built files from the 'builder' stage (/app/dist) 
# to the Nginx public directory
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]