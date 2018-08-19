FROM java:8
FROM redis
COPY . .
EXPOSE 6379
CMD ["redis-server"]
