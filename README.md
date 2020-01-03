# fuchu

## Infrastructure
The project infrastructure is organized via docker-compose tool and includes the following services:
- zookeeper
- kafka
- metrics

Data persists in git ignored `data` directory. Configurations placed in `conf/<service_name>` directory.

### Zookeeper
Based on official [zookeeper](https://hub.docker.com/_/zookeeper) image.

Exposed ports:
- 2181 — client port.

### Kafka
Based on [wurstmeister/kafka](https://hub.docker.com/r/wurstmeister/kafka/) image.

Exposed ports:
- 9092 

### Metrics
Based on [gographite/docker-go-graphite](https://hub.docker.com/r/gographite/docker-go-graphite) image.

Use go-carbon and whisper in backend and grafana for front-end dashboards.

Exposed ports:
- 3000 — grafana webserver.
- 2003 — carbon receiver port for plaintext.
- 2004 — carbon receiver port for pickle.

See [graphite docs](https://graphite.readthedocs.io/en/latest/feeding-carbon.html#the-plaintext-protocol) to know how to send metrics into carbon or check operability.
