
FROM node:18-alpine

WORKDIR /crud-app

COPY package.json .

RUN npm install

COPY . .

CMD [ "npm", "run", "dev" ]