# JAVAX-MCR - Microservice alkalmazás felépítése Spring Boot keretrendszerrel Docker környezetben

Ez a repository tartalmazza a JAVAX-MCR tanfolyam gyakorlati feladatait.

Tartalma:

* [gyakorlati-feladat.md](gyakorlati-feladat.md) - Gyakorlati feladatok szöveges leírása.
* [demos-on-videos](demos-on-videos) - Videókon szereplő gyakorlati feladatok.
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
* Twelve Factor App
* Bevezetés a Docker használatába
* Java alkalmazások Dockerrel (`employees-m01-l11-docker`)
* Docker Layers (`employees-m01-l12-dockerlayers`)
* Feltöltés GIT repository-ba
* REST webszolgáltatások - GET művelet (`employees-m02-l01-get`)
* GET műveletek paraméterezése (`employees-m02-l02-getparams`)
* REST webszolgáltatások POST és DELETE művelet - gyakorlat - Create (`employees-m02-l03-p01-create`)
* REST webszolgáltatások POST és DELETE művelet - gyakorlat - Update és delete (`employees-m02-l03-p02-update-delete`)
* Státuszkódok és hibakezelés (`employees-m02-l04-status`)
* Integrációs tesztelés - gyakorlat - MockMVC (`employees-m02-l05-p01-mockmvc`)
* Integrációs tesztelés - gyakorlat - RestTemplate (`employees-m02-l05-p02-resttemplate`)
* Swagger UI (`employees-m02-l06-swagger`)
* Tesztelés Rest Assured használatával (`employees-m02-l07-restassured`)
* Rest Assured séma validáció (`employees-m02-l08-restassured-validation`)
* Content Negotiation (`employees-m02-l09-content-negotiation`)
* Validáció (`employees-m02-l10-p01-validation`)
* Validáció - gyakorlat - saját validáció (`employees-m02-l10-p02-own-validation`)
* Spring Boot konfiguráció - (`javax-mcr-m03-l01-configuration`)
* Spring Boot naplózás - (`javax-mcr-m03-l02-logging`)
* Spring JdbcTemplate - (`javax-mcr-m04-l01-jdbctemplate`)
* Spring Data JPA - (`javax-mcr-m04-l02-spring-data-jpa`)


