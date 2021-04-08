[![Travis Build Status](https://travis-ci.org/rvallet/ocp9-projetB4.svg?branch=main)](https://travis-ci.org/rvallet/ocp9-projetB4) [![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=rvallet_ocp9-projetB4&metric=coverage)](https://sonarcloud.io/dashboard?id=rvallet_ocp9-projetB4) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=rvallet_ocp9-projetB4&metric=alert_status)](https://sonarcloud.io/dashboard?id=rvallet_ocp9-projetB4) 
# MyERP
Tests unitaires et tests d'intégration sur un système de facturation et de comptabilité.

## Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
*   `dev` : environnement de développement
*   `src` : code source de l'application


## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)


### Lancement

    cd docker/dev
    docker-compose up


### Arrêt

    cd docker/dev
    docker-compose stop


### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up
