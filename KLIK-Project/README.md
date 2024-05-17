## Description

Test environment for UI testing of application https://github.com/plmaskw2/socialmediawebapp

## Requirements

To run this application, you need:
- Java JDK '21.0.2'
- Maven '3.9.6'

(recommended)
- Docker installed on remote machine
- Basic understanding of Docker Compose


## Selenoid
Be aware about browser and docker images versions. Below versions are examples.
To run selenoid server:
1. Create 'docker-compose.yml' and 'browsers.json' file on remote machine.
2. Put the code into files:

'docker-compose.yml' 
```
version: '3'
services:
  selenoid:
    image: "aerokube/selenoid:latest-release"
    network_mode: bridge
    volumes:
      - "/home/home/Server/selenoid:/etc/selenoid/:ro"
      - "/var/run/docker.sock:/var/run/docker.sock"
      - "/home/home/Server/selenoid/video:/opt/selenoid/video"
      - "/home/home/Server/selenoid/logs:/opt/selenoid/logs"
    environment:
      - OVERRIDE_VIDEO_OUTPUT_DIR=/home/home/Server/selenoid/video
    command: ["-conf", "/etc/selenoid/browsers.json", "-video-output-dir", "/opt/selenoid/video", "-log-output-dir", "/opt/selenoid/logs"]
    ports:
      - "5555:4444"
  selenoid-ui:
    image: "aerokube/selenoid-ui"
    network_mode: bridge
    links:
      - selenoid
    ports:
      - "8090:8080"
    command: ["--selenoid-uri", "http://selenoid:4444"]
```

'browsers.json'
```
{
  "chrome": {
    "default": "124.0",
    "versions": {
      "124.0": {
        "image": "selenoid/vnc_chrome:124.0",
        "port": "4444",
        "path": "/",
        "tmpfs": {
          "/tmp": "size=2g"
        }
      }
    }
  },
  "firefox": {
     "default": "63.0",
     "versions": {
	"63.0": {
         "image": "selenoid/firefox:63.0",
         "port": "4444",
         "path": "/wd/hub",
         "tmpfs": {
           "/tmp": "size=128m"
        }
      }
    }
  }
}
```


4. Pull images for browsers:
   ```
   docker pull selenoid/vnc_chrome:123.0"
   docker pull selenoid/firefox:63.0
   ```
6. Run the following command in your terminal:
   ```
   docker-compose up
   ```
7. Once the containers are up and running, you can access the 'selenoid-ui' at <host_ip>:8090.

## Selenium Grid
Be aware about browser and docker images versions. Below versions are examples.
To run Selenium Grid server:
1. Create 'docker-compose.yml' file on remote machine.
2. Put the code into files:

'docker-compose.yml' 
```
version: '3.8'

services:
 selenium-hub:
  image: selenium/hub:latest
  container_name: selenium-hub
  network_mode: grid_default
  ports: 
    - "4442-4444:4442-4444"
 selenium-node-chrome:
  image: selenium/node-chrome:123.0
  network_mode: grid_default
  shm_size: 2gb
  volumes:
   - /dev/shm:/dev/shm
  depends_on:
   - selenium-hub
  ports:
   - "6900:5900"
  environment:
   - SE_EVENT_BUS_HOST=selenium-hub
   - SE_EVENT_BUS_PUBLISH_PORT=4442
   - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
```
6. Run the following command in your terminal:
   ```
   docker-compose up
   ```
7. Once the containers are up and running, you can access the 'selenium-hub' UI at <host_ip>:4444.
