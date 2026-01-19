# Gyakorlati feladatok

A gyakorlati feladatok egymásra épülnek.
Létre kell hozni egy alkalmazást, mely kedvenc helyeket
tart nyilván. Az alkalmazás neve ezért _Locations_.

Arra bíztatlak, hogy bár a videóban fix, hogy 
melyik JDK-val és a különböző library-k melyik verziójával lett felvéve, ettől függetlenül 
mindig használd a legfrissebb fejlesztőeszközt, JDK-t és library-ket! 
Ezért van, ami nem pont úgy fog működni, ahogy a videóban szerepel, ilyenkor kérd a mentor segítségét!

Feltelepítendő szoftverek:

* JDK
* IntelliJ IDEA Ultimate (akár próbaverzió)
* Docker Desktop
* Postman

## Bevezetés a Spring Framework és Spring Boot használatába

Ennek a feladatnak az elvégzése opcionális. Akkor csináld meg,
ha érdekel, hogy hozz létre egy Spring Frameworkre épülő
projektet Spring Boot nélkül. Ezt ma már csak legacy alkalmazásoknál
használjuk, új projektet Spring Boot használatával indítunk.

Hozz létre egy `locations-spring` projektet, benne egy `LocationsController`
osztályt, mely visszaadja a kedvenc helyeket, egyelőre Stringként!

Egy kedvenc helyet a `Location` osztály reprezentál. Rendelkezik egy azonosítóval, névvel
és két koordinátával (rendre `Long id`, `String name`, `double latitude`, `double longitude`).

Ezek egy listája legyen a controllerben. A `getLocations()` metódus ezeket adja vissza.
Implementáld a `Location` osztály `toString()` metódusát!

## Bevezetés a Spring Boot használatába

Hozz létre egy `locations` projektet! 

Egy kedvenc helyet a `Location` osztály reprezentál. Rendelkezik egy azonosítóval, névvel
és két koordinátával (rendre `Long id`, `String name`, `double latitude`, `double longitude`).

A `locations` csomagba dolgozz!

Hozz létre egy `LocationsController`
osztályt, mely visszaadja a kedvenc helyeket, egyelőre Stringként! A metódus neve
legyen `getLocations()` és a `/locations` címen adja vissza a kedvenc helyek listáját!

## Spring Beanek

Hozz létre egy `LocationsService` osztályt,
melyben legyen egy `List<Location>`! Ezt töltsd fel kezdeti értékekkel!
A `LocationsController`-ből távolítsd el a listát, és a `getLocations()`
metódusa hívja meg a `LocationsService` `getLocations()` metódusát!
Ez egy `List<Location>` adatszerkezettel térjen vissza, melyet a 
controller alakít át egy Stringgé.

## Konfiguráció Javaban

A `LocationsService` osztályról távolítsd el a `@Service` annotációt,
és próbáld `@Bean` annotációval ellátott metódusban példányosítani!

Ha végeztél, térj vissza az annotációval történő konfigurációra,
ugyanis saját beanek esetén ezek használata javasolt. Később lesz
olyan bean, melyet Java konfigurációval kell felvenni.

## Build és futtatás Mavennel

Próbáld ki a buildelést, futtatást Mavenből, parancssorból!
Build után próbáld futtatni az alkalmazást a parancssorból!
Használd az `mvnw` szkriptet!

## Build és futtatás Gradle használatával

Hozz létre egy `locations-gradle` külön projektet, másold át a
`locations` projektből a megfelelő fájlokat!

Próbáld meg megnyitni fejlesztőeszközben!

Próbáld meg buildelni és futtatni parancssorból!
Használd a `gradlew` szkriptet!

## Unit és integrációs tesztek

Írj unit tesztet a `LocationsService` és `LocationsController`
osztályra! Írj integrációs tesztet, mellyel a `LocationsController`-t
hívod meg!

## Developer Tools

Próbáld ki a Developer Tools használatát! Próbáld meg, hogy működik
az automatikus újraindítás és a Live Reload! Ez utóbbihoz böngésző
plugint is kell telepítened!

## Twelve Factor App

Ehhez a leckéhez nincs gyakorlati feladat.

Böngészd végig a [The Twelve-Factor App](https://12factor.net/) honlapját!

## Bevezetés a Docker használatába

Próbálgasd végig a gyakorlati videó alapján a Docker parancsokat,
futtatva az NGINX webszervert!

## Java alkalmazások Dockerrel

Csomagold be és futtasd a _Locations_ alkalmazást Docker konténerben!

## Docker Layers

Csomagold be és futtasd a _Locations_ alkalmazást megfelelően layerelt Docker konténerben!

## Feltöltés GIT repository-ba

Hozz létre egy publikus Git repo-t, és töltsd fel a megoldásod!
Küldd el a mentornak, örülni fog neki!

## REST webszolgáltatások - GET művelet

Módosítsd az elvárt architektúrának megfelelően a _Locations_ alkalmazást!
A `LocationsController` osztályon legyen `@RestController`, a `getLocations()` metóduson legyen
`@GetMapping` annotáció! Hozd létre a `LocationDto` osztályt!
Vezesd be a Lombok használatát!
A DTO és az entitás közötti konvertálásra használj ModelMappert!

Ennek megfelelően változtasd a unit és integrációs teszteket, ha
arra szükség van!

## GET műveletek paraméterezése

Lehessen megadni, hogy szűrni lehessen a kedvenc hely nevére!

Lehessen lekérni kedvenc helyeket id alapján is!

Szűrésre használj Java 8 streameket! 

## REST webszolgáltatások POST és DELETE művelet

### Create

Id generálására innentől használj `AtomicLong` osztályt!

Írd meg, hogy lehessen felvenni új kedvenc helyet! Ehhez hozz létre
egy `CreateLocationCommand` osztályt is!

### Update és delete

Lehessen kedvenc helyet módosítani és törölni is. Ehhez szükséged lesz egy
`UpdateLocationCommand` osztályra is. Lehessen módosítani a nevet és
a koordinátákat is.

## MapStruct

Írd át a projektet úgy, ne ModelMapperrel, hanem MapStructtal működjön!

## Státuszkódok és hibakezelés

Módosítsd az alkalmazásod, hogy létrehozáskor `201`-es státuszkóddal,
törléskor `204`-es státuszkóddal térjen vissza!

Módosítsd az alkalmazásod, hogy az RFC szabványnak megfelelő
JSON legyen az üzenet törzsében!

Módosítsd, hogy nem található kedvenc hely esetén `404`-es státuszkód jöjjön vissza!
Használj saját kivételt, pl. `LocationNotFoundException`!

## Státuszkódok és hibakezelés problem-spring-web-starterrel

Módosítsd úgy az alkalmazásod, hogy a hibakezelés `problem-spring-web-starter`-rel történjen!

## Integrációs tesztelés

### MockMVC

Írj legalább egy Controller metódusra MockMVC integrációs tesztet!
Amennyiben időd engedi, mindegyik metódusra írj, legyen közel 100%-os a controller
réteg lefedettsége!

### RestTemplate

Írj legalább egy Controller metódusra RestTemplate integrációs tesztet!
Amennyiben időd engedi, mindegyik metódusra írj, legyen közel 100%-os a controller
réteg lefedettsége!

### WebClient

Írj legalább egy Controller metódusra WebClient integrációs tesztet!
Amennyiben időd engedi, mindegyik metódusra írj, legyen közel 100%-os a controller
réteg lefedettsége!

## Swagger UI

Integráld az alkalmazásodba a Swagger UI-t! Paraméterezd, hogy
egy jól dokumentált API-ja legyen az alkalmazásnak! Figyelj a
státuszkódokra is!

OpenAPI dokumentumot importáld a Postmanbe, és teszteld a 
REST webszolgáltatásokat!

## Tesztelés Rest Assured használatával

Írj legalább egy Controller metódusra integrációs tesztet!
Amennyiben időd engedi, mindegyik metódusra írj, legyen közel 100%-os a controller
réteg lefedettsége!

## Rest Assured séma validáció

Az OpenAPI dokumentumból kimásolt séma alapján a RestAssured tesztesetekben
assert-álj JSON Schema szerint!

## Content Negotiation

Implementáld, hogy a REST webszolgáltatások képesek legyenek XML
formátumú választ is visszaadni. Problémát csak a `List<Location>`
visszatérési érték fog okozni, itt készíts egy burkoló DTO-t!
Ez alapján feltételezhető, hogy a teszteseteket is át kell írni!

## Validáció

### Beépített validációk

A `CreateLocationCommand` és `UpdateLocationCommand` esetén a következő
validációkat építsd be!
A név nem lehet üres, a latitude értékének -90 és +90
között kell lennie, a longitude értékének -180 és +180 között kell lennie!
Írj rá tesztesetet!

### Validáció problem-spring-web-starterrel

Módosítsd úgy az alkalmazásodat, hogy a validációt a `problem-spring-web-starter` kezelje!

### Saját validáció

Hozz létre egy saját validációs annotációt `@Coordinate` néven!
Ennek legyen egy `Type` nevű enum paramétere, melynek értékei
`Type.LAT`, `Type.LON`. Írj hozzá validációt, mely leellenőrzi, hogy a
koordináták megfelelőek-e! (Csak `double` típusra rakható.) Írj rá tesztesetet!

### Validáció problem-spring-web-starterrel

Alakítsd át úgy az alkalmazásodat, hogy a validáció innentől kezdve a 
`problem-spring-web-starter` segítségével történjen!

## Spring Boot konfiguráció

Konfigurációból állítani lehessen, hogy a `LocationService`
a felvett és módosított kedvenc hely nevét automatikusan nagybetűsítse-e!
Ha ennek értéke `true`, akkor amennyiben kisbetűvel kerülne
felvitelre, a service rétegban automatikusan nagybetűsítve lesz!

## Spring Boot naplózás

Minden létrehozó, módosító és törlő művelet esetén legyen egy naplóbejegyzés!
A naplóban szerepeljen az id is!

## Spring JdbcTemplate

Vezess be egy perzisztens réteget, mely JdbcTemplate-tel dolgozik.
Használj H2 adatbázist!
A service rétegből távolítsd el a memóriában tárolást, és hívjon tovább
a perzisztens rétegbe!

## Spring Data JPA

Cseréld le a perzisztens réteget Spring Data JPA alapúra!

## MariaDB

Indíts el egy MariaDB adatbázist! Konfiguráld az alkalmazást, hogy
ne H2 adatbázisba mentse az adatokat, hanem MariaDB-be!

Amennyien a PostgreSQL-hez jobban értesz, használhatod azt is!

## Integrációs tesztelés

### Repository réteg tesztelése

Írj egy repository-t tesztelő integrációs tesztet! 
Amennyiben van időd, érdemes közel 100%-os lefedettséget produkálni, 
azaz minden felhasznált metódust tesztelni.

### Tesztelés MariaDB-n

Állítsd át, hogy az integrációs tesztek egy másik
porton futó MariaDB adatbázison fussanak!

Írj legalább egy controller metódusra teljes integrációs tesztet,
mely a teljes alkalmazást meghajtja!

Amennyiben időd engedi, mind az öt metódust teszteld le!

### Teljes alkalmazás integrációs tesztelése

Figyeld meg, ha egyszerre több tesztet futtatsz,
elbukhatnak a tesztesetek! Ez azért van, mert a tesztek között állapot kerül
átvitelre. Ezért minden integrációs teszt előtt, mely használja
az adatbázist, ürítsd ki a `locations` tábla tartalmát!

## Alkalmazás futtatása Dockerben MariaDB-vel

Indíts el egy harmadik MariaDB adatbázist (eddig egy kellett a fejlesztéshez,
egy a tesztesetekhez), és indítsd el az alkalmazást egy Docker
konténerben, ami ehhez az adatbázishoz kapcsolódik!

## Alkalmazás futtatása Docker Compose-zal

Hozz létre egy `docker-compose.yaml` állományt, mely egyszerre futtatja 
az adatbázist és az alkalmazást tartalmazó Docker konténereket!

## Séma inicializálás Flyway-jel

Állítsd be, hogy a sémát (táblát) ne a Hibernate hozza létre, hanem a Flyway!

Amennyiben nem Flywayt használnál, kipróbálhatod a Liquibase-t is.

