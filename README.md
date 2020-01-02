# fuchu

## Infrastructure
The project infrastructure is organized via docker-compose tool and includes the following services:
- metrics

Data persists in git ignored *data* directory. Configurations placed in *conf/<service_name>* directory.

### Metrics
Based on [gographite/docker-go-graphite](!https://hub.docker.com/r/gographite/docker-go-graphite) image.
Use go-carbon and whisper in backend and grafana for front-end dashboards.
Grafana webserver port is 3000 by default.
Carbon receiver port is 2003 for plaintext and 2004 for pickle.
