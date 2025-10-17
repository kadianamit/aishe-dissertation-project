# Stage 1: Build Angular app using Node.js 20
FROM node:20 AS build
WORKDIR /app

# Copy package files & install dependencies
COPY package*.json ./
RUN npm ci --legacy-peer-deps

# Copy project files & build Angular (limit Node memory to 2GB to avoid OOM)
COPY . .
ENV NODE_OPTIONS="--max-old-space-size=2048"
# IMPORTANT: build with base-href=/ so the app is served from the root
RUN npm run build -- --configuration production --base-href=/

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Copy custom nginx config (SPA fallback + correct static handling)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built app to Nginx web root
COPY --from=build /app/dist/aishenew /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

