# JAVAX-MCR - Microservice alkalmazás felépítése Spring Boot keretrendszerrel Docker környezetben

Ez a repository tartalmazza a JAVAX-MCR tanfolyam gyakorlati feladatait.

Tartalma:

* [javax-mcr-lab.md](javax-mcr-lab.md) - Gyakorlati feladatok szöveges leírása.
* [labs](labs) - Gyakorlati feladatok referenciamegoldása.
* [demos-on-videos](demos-on-videos) - Videókon szereplő gyakorlati feladatok.
* [demos-on-videos-spring-boot-3](demos-on-videos-spring-boot-3) - Spring Boot 3-ra frissített gyakorlati feladatok.
* [slides/javax-mcr-slides.md](slides/javax-mcr-slides.md) - Videón szereplő, de azóta módosított slide-ok.
* [apps/employees-addresses](apps/employees-addresses) - _Addresses_ alkalmazás, melyhez REST-en keresztül lehet integrálni az _Employees_ alkalmazást. 
 Alkalmazottakhoz tartozó címeket lehet lekérdezni.
 A forráskódra valójában nincs szükség, hiszen Docker konténerben indítható.
* [apps/employees-eventstore](apps/employees-eventstore) - _EventStore_ alkalmazás, melyhez REST-en és JMS-en keresztül lehet integrálni az _Employees_ alkalmazást. 
 Eseményekről lehet az alkalmazást értesíteni, amiket naplóz.
 A forráskódra valójában nincs szükség, hiszen Docker konténerben indítható.
* [apps/employees-timesheet](apps/employees-timesheet) - _TimeSheet_ alkalmazás, melyhez gRPC-vel lehet kapcsolódni.
 Alkalmazottakhoz tartozó munkaidő nyilvántartásokat tartalmazza.
 A forráskódra valójában nincs szükség, hiszen Docker konténerben indítható.

## Videón szereplő gyakorlati feladatok

* Bevezetés a Spring Framework és Spring Boot használatába (`employees-m01-l01-spring`)
* Bevezetés a Spring Boot használatába (`employees-m01-l02-intro`)
* Spring Beanek (`employees-m01-l03-beans`)
* Konfiguráció Javaban (`employees-m01-l04-config`)
* Build és futtatás Mavennel
* Build és futtatás Gradle használatával (`employees-m01-l06-gradle`)
* Unit és integrációs tesztek (`employees-m01-l07-tests`)
* Developer Tools (`employees-m01-l08-devtools`)
* Twelve-Factor App
* Bevezetés a Docker használatába
* Java alkalmazások Dockerrel (`employees-m01-l11-docker`)
* Docker Layers (`employees-m01-l12-dockerlayers`)
* Feltöltés GIT repository-ba
* Lombok (`demos-on-videos-spring-boot-3/lombok-demo`)
* REST webszolgáltatások
    * REST webszolgáltatások - GET művelet (`employees-m02-l01-get`)
    * GET műveletek paraméterezése (`employees-m02-l02-getparams`)
    * Több URL paraméter kezelése (`demos-on-videos-spring-boot-3/employees-m02-l02-p02-get-more-params`)
    * REST webszolgáltatások POST és DELETE művelet - gyakorlat - Create (`employees-m02-l03-p01-create`)
    * REST webszolgáltatások POST és DELETE művelet - gyakorlat - Update és delete (`employees-m02-l03-p02-update-delete`)
    * REST legjobb gyakorlatok
    * MapStruct (`employees-m02-l03-p03-mapstruct`)
    * Státuszkód kezelés (`demos-on-videos-spring-boot-3/employees-m02-l04-p01-status`)
    * Hibakezelés (`demos-on-videos-spring-boot-3/employees-m02-l04-p02-spring-problem`)
    * Problem Details (`demos-on-videos-spring-boot-3/employees-m02-l04-p02-spring-problem`)
    * Státuszkódok és hibakezelés (Deprecated - Spring Boot 2) (`demos-on-videos/employees-m02-l04-p01-status`)
    * Integrációs tesztelés - gyakorlat - MockMVC (`employees-m02-l05-p01-mockmvc`)
    * Integrációs tesztelés - gyakorlat - RestTemplate (`employees-m02-l05-p02-resttemplate`)
    * Integrációs tesztelés WebClient használatával - gyakorlat (`employees-m02-l05-p03-webclient`)
    * Swagger UI (`demos-on-videos-spring-boot-3/employees-m02-l06-p01-swagger`)
    * OpenAPI Generator (`demos-on-videos-spring-boot-3/employees-m02-l06-p02-openapi-generator`)
    * Tesztelés Rest Assured használatával (`employees-m02-l07-restassured`)
    * Rest Assured séma validáció (`employees-m02-l08-restassured-validation`)
    * Content Negotiation (`employees-m02-l09-content-negotiation`)
    * Validáció (Deprecated - Spring Boot 2) (`demos-on-videos/employees-m02-l10-p01-validation`)
    * Validáció (`demos-on-videos-spring-boot-3/employees-m02-l10-p01-validation`)
    * Validáció és a Problem Details (`demos-on-videos-spring-boot-3/employees-m02-l10-p02-validation-spring-problem`)
    * Validáció - gyakorlat - saját validáció (`employees-m02-l10-p03-own-validation`)
* Konfiguráció és naplózás
    * Spring Boot konfiguráció - (`javax-mcr-m03-l01-configuration`)
    * Spring Boot naplózás - (`javax-mcr-m03-l02-logging`)
* Adatbáziskezelés
    * Spring JdbcTemplate - (`javax-mcr-m04-l01-jdbctemplate`)
    * Spring Data JPA - (`javax-mcr-m04-l02-spring-data-jpa`)
    * MariaDB
    * Integrációs tesztelés
* NoSQL
    * MongoDB (`employees-m05-mongodb`)
* Security
    * OAUTH 2.0 KeyCloak szerverrel (`employees-m06-keycloak`)
* Integráció
    * RestTemplate (`employees-m07-l01-p01-resttemplate`)
    * WebClient (`employees-m07-l01-p02-webclient`)
    * WireMock (`employees-m07-l02-p01-wiremock`)
    * Spring Cloud Contract WireMock (`employees-m07-l02-p02-cloud-wiremock`)
    * JMS üzenet küldése (`employees-m07-l03-jms`)
    * JMS üzenet fogadása
* Actuator (`employees-m08-actuator`)
    * Actuator 
    * Információk megjelenítése
    * Naplózás
    * Metrics
    * Metrics Prometheus Monitoring eszközzel
    * Audit Events
* Continuous Integration és Delivery (`employees-m09-cicd`)
    * Continuous Delivery Jenkins Pipeline-nal


