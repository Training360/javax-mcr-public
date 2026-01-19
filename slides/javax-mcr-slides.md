
class: inverse, center, middle

# Microservice alkalmazás felépítése Spring Boot keretrendszerrel Docker környezetben

---

class: inverse, center, middle

# Bevezetés

---

## Referenciák

* Craig Walls: Spring in Action, Fifth Edition (Manning)
* Kevin Hoffman: Beyond the Twelve-Factor App (O'Reilly)
* [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

---

class: inverse, center, middle

# Bevezetés a Spring Framework használatába

---

## Spring Framework céljai

* Keretrendszer nagyvállalati alkalmazásfejlesztésre
* Keretrendszer: komponensek hívása, életciklusa
* Nagyvállalati alkalmazás: Java SE által nem támogatott tulajdonságok
    * Spring Framework nem magában ad választ, hanem integrál egy vagy <br /> több megoldást

---

## Nagyvállalati alkalmazás - 1.

* Komponensek életciklus kezelése és kapcsolatok
* Távoli elérés
* Többszálúság
* Perzisztencia
* Tranzakció-kezelés
* Aszinkron üzenetkezelés
* Ütemezés

---

## Nagyvállalati alkalmazás - 2.

* Integráció
* Auditálhatóság
* Konfigurálhatóság
* Naplózás, monitorozás és beavatkozás
* Biztonság
* Tesztelhetőség

---

## Spring Framework <br /> tulajdonságai - 1.

* Komponensek, melyeket konténerként tartalmaz <br /> (konténer: application context)
* Konténer vezérli a komponensek életciklusát <br /> (pl. példányosítás)
* Konténer felügyeli a komponensek közötti kapcsolatot <br /> (Dependency Injection, Inversion of Control)
* Komponensek és kapcsolataik leírása több módon: <br /> XML, annotáció, Java kód
* Pehelysúlyú, non invasive (POJO komponensek)
* Aspektusorientált programozás támogatása

---

## Spring Framework <br /> tulajdonságai - 2.

* 3rd party library-k integrálása az egységes modellbe
* Glue kód
* Boilerplate kódok eliminálása
* Fejlesztők az üzleti problémák <br /> megoldására koncentráljanak

---

## Application Context

![Application Context](images/spring-app-context.png)

---

## Háromrétegű webes alkalmazás

* Nem kizárólag erre, de ez a fő felhasználási terület
* Rétegek
    * Repository
    * Service
    * Controller
* Hangsúlyos része a Spring MVC webes alkalmazások írására, <br /> HTTP felé egy absztrakció
* HTTP kezelését web konténerre bízza, <br /> pl. Tomcat, Jetty, stb.

---

## Rétegek

<img src="images/spring-app-context-layers.png" alt="Rétegek" width="500">

---

class: inverse, center, middle

# Bevezetés a Spring Boot használatába

---

## Spring Boot - 1.

* Autoconfiguration: classpath-on lévő osztályok, 3rd party library-k, környezeti változók és
  egyéb körülmények alapján komponensek automatikus <br /> létrehozása és konfigurálása
* Intelligens alapértékek, convention over configuration, <br /> konfiguráció nélkül is működjön
    * Saját konfiguráció írása csak akkor, ha eltérnénk az alapértelmezettől
    * Automatically, automagically
* Self-contained: az alkalmazás tartalmazza <br /> a web konténert is (pl. Tomcat)

---

## Spring Boot - 2.

* Nagyvállalati üzemeltethetőség: Actuatorok
    * Pl. monitorozás, konfiguráció, beavatkozás, <br /> naplózás állítása, stb.
* Gyors kezdés: Spring Initializr [https://start.spring.io/](https://start.spring.io/)
* Starter projektek: függőségek, <br /> előre beállított verziószámokkal (tesztelt)
* Ezért különösen alkalmas microservice-ek fejlesztésére

---

## Könyvtárstruktúra

```shell
├── .gitignore
├── .mvn
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── training
    │   │       └── employees
    │   │           └── EmployeesApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── training
                └── employees
                    └── EmployeesApplicationTests.java
```

---

## pom.xml

* Parent: `org.springframework.boot:spring-boot-starter-parent`
    * Innen öröklődnek a függőségek, verziókkal együtt
* Starter: `org.springframework.boot:spring-boot-starter-web`
    * Jackson, Tomcat
* Teszt támogatás: `org.springframework.boot:spring-boot-starter-test`
    * JUnit 5, Mockito, AssertJ, Hamcrest, [XMLUnit](https://www.xmlunit.org/), [JSONassert](https://github.com/skyscreamer/JSONassert), [JsonPath](https://github.com/json-path/JsonPath)

---

class: inverse, center, middle

# Spring Beanek

---

## Spring Beanek

* Spring bean: tud róla a Spring konténer
* Spring példányosítja
* Spring állítja be a függőségeit
* POJO
* Rétegekbe rendezve
* Alapértelmezetten singleton, egy példányban jön létre

---

## Application

* Alkalmazás belépési pontja `main()` metódussal
* `@SpringBootApplication`:
    * `@EnableAutoConfiguration`: autoconfiguration bekapcsolása
    * `@SpringBootConfiguration`: `@Configuration`: maga az osztály is <br /> tartalmazhasson további konfigurációkat
    * `@ComponentScan`: `@Component`, <br /> `@Repository`, `@Service`, `@Controller`
    annotációval ellátott

```java
@SpringBootApplication
public class EmployeesApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeesApplication.class,
      args);
  }

}
```

---

## Controller komponensek

* Spring MVC része
* Felhasználóhoz legközelebb lévő réteg, felelős a <br /> felhasználóval való
  kapcsolattartásért
    * Adatmegjelenítés és adatbekérés
* POJO
* Annotációk erős használata
* Nem feltétlenül van Servlet API függősége
* Metódusok neve, paraméterezése flexibilis
* Gyakran REST végpontok kialakítására használjuk

---

## Annotációk

* `@Controller`: megtalálja a component scan, Spring MVC felismeri
* `@RequestMapping` milyen URL-en hallgat
    * Ant-szerű megadási mód (pl. `/admin/*.html`)
    * Megadható a HTTP metódus a `method` paraméterrel
* `@ResponseBody` visszatérési értékét azonnal a HTTP válaszba kell írni <br /> (valamilyen szerializáció után)

---

## Controller

```java
@Controller
public class EmployeesController {

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }
}
```

---

## Service

```java
@Service
public class EmployeesService {

    public String helloWorld() {
        return "Hello World at " + LocalDateTime.now();
    }
}
```

---

## Kapcsolatok

* Dependency injection
* Definiáljuk a függőséget, a konténer állítja be
* Függőségek definiálása:
    * Attribútum
    * Konstruktor
    * Metódus
* Legjobb gyakorlat: kötelező függőség konstruktorban
* Ha csak egy konstruktor, automatikusan megtörténik <br /> a dependency injection
* Egyéb esetben `@Autowired` annotáció

---

## Függőség a controllerben

```java
@Controller
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld() {
        return employeesService.helloWorld();
    }
}
```

---

class: inverse, center, middle

# Statikus állományok

---

## Statikus állomány

* `src/main/resources/static` könyvtárban
* Welcome Page: `index.html`

---

## WebJars

* JavaScript könyvtárak jar fájlba csomagolva
* `META-INF/resources` könyvtárban
* Hivatkozás pl.: `/webjars/bootstrap/4.5.2/css/bootstrap.css`

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>4.5.2</version>
</dependency>
```

---

## Version agnostic

Hivatkozás pl.: `/webjars/bootstrap/css/bootstrap.css`

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>webjars-locator-core</artifactId>
</dependency>
```

---

class: inverse, center, middle

# Konfiguráció Javaban

---

## Java konfiguráció

* Ekkor nem kell a `@Service` annotáció: non-invasive
* `@Configuration` által ellátott osztályban, itt `EmployeesApplication`
* Legjobb gyakorlat: saját beanek component scannel, <br /> 3rd party library-k Java konfiggal
* Legjobb gyakorlat: rétegenként külön `@Configuration` <br /> annotációval ellátott osztály

---

## Java konfiguráció példa

```java
@Bean
public EmployeesService employeesService() {
  return new EmployeesService();
}
```

```java
public class EmployeesService {

    public String helloWorld() {
        return "Hello Dev Tools at " + LocalDateTime.now();
    }
}
```

---

class: inverse, center, middle

# Build és futtatás Mavennel

---

## Build parancssorból

* Build parancssorból Mavennel

```shell
mvnw clean package
```

* `spring-boot-maven-plugin`: átcsomagolás, beágyazza a web konténert
* `employees-0.0.1-SNAPSHOT.jar.original` és <br /> `employees-0.0.1-SNAPSHOT.jar`

---

## Futtatás parancssorból

* Futtatás parancssorból Mavennel

```shell
mvnw spring-boot:run
```

* Futtatás parancssorból

```shell
java -jar employees-0.0.1-SNAPSHOT.jar
```

---

class: inverse, center, middle

# Build és futtatás Gradle használatával

---

## Gradle használata

* A [start.spring.io](https://start.spring.io) támogatja a Gradle alapú projekt generálását
* A [io.spring.dependency-management](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)
  Gradle plugin <br /> lehetővé tesz Maven-szerű
  függőségkezelést <br /> - csak a verziókat deklarálja, de a függőséget explicit kell megadni
* A [org.springframework.boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/#reacting-to-other-plugins-dependency-management)
  Gradle plugin <br /> képes a jar és war előállítására, figyelembe véve az előző plugint
* Generál Gradle wrappert is - ha nincs Gradle telepítve az adott gépre
* JUnit 5 függőség

---

## Gradle taskok

```shell
gradle build

gradle -i build

gradle bootRun
```

A `-i` kapcsoló INFO szintű naplózást állít

---

class: inverse, center, middle

# Unit és integrációs tesztek

---

## Unit tesztelés

* JUnit 5
* Non invasive - POJO-ként tesztelhető
* AssertJ, Hamcrest classpath-on

```java
@Test
void testSayHello() {
  EmployeesService employeesService = new EmployeesService();
  assertThat(employeesService.sayHello())
    .startsWith("Hello");
}
```

---

## Unit tesztelés függőséggel

* Mockito classpath-on

```java
@ExtendWith(MockitoExtension.class)
public class EmployeesControllerTest {

  @Mock
  EmployeesService employeesService;

  @InjectMocks
  EmployeesController employeesController;

  @Test
  void testSayHello() {
    when(employeesService.sayHello())
      .thenReturn("Hello Mock");
    assertThat(employeesController.helloWorld())
      .startsWith("Hello Mock");
  }
}
```

---

## Integrációs tesztek

* Üres teszt a konfiguráció ellenőrzésére, elindul-e az application context
* `@SpringBootTest` annotáció: tartalmazza <br /> az `@ExtendWith(SpringExtension.class)` annotációt
* Tesztesetek között cache-eli az application contextet
* Beanek injektálhatóak az `@Autowired` annotációval

---

## Integrációs tesztek példa

```java
@SpringBootTest
class EmployeesControllerIT {

  @Autowired
  EmployeesController employeesController;

  @Test
  void testSayHello() {
    String message = employeesController
      .helloWorld();
    assertThat(message).startsWith("Hello");
  }
}
```

---

class: inverse, center, middle

# Developer Tools

---

## Developer Tools

* Felülírt property-k (Property Defaults): pl. template cache kikapcsolása
* Automatikus újraindítás
* LiveReload
* Globális, felhasználónkénti beállítások (Global Settings)
* Távoli alkalmazáson osztály frissítése (Remote Applications)


```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

---

## Automatikus újraindítás

* Ha változik valami a classpath-on
* IDE függő
    * Eclipse-nél mentésre
    * IDEA-nál _Build / Rebuild Project_ (`Ctrl + F9` billentyűzetkombináció)
* Két osztálybetöltő, az egyik a saját kód, másik a függőségek - <br /> változás esetén csak az elsőt tölti újra, függőség változása esetén <br /> manuálisan kell újraindítani
* Újraindítja a web konténert is

```properties
spring.devtools.restart.poll-interval=2s
spring.devtools.restart.quiet-period=1s
```

---

## LiveReload

* Böngésző plugin szükséges hozzá
* A Spring Boot elindít egy LiveReload szervert
* LiveReload plugin felépít egy WebSocket kapcsolatot
* Ha változik valami, újratöltés van
* Pl. statikus állomány esetén (`src/main/resources/static/*.html`)
* IDE függő
    * Eclipse-nél mentésre
    * IDEA-nál _Build / Rebuild Project_ <br /> (`Ctrl + F9` billentyűzetkombináció)

---

## Global settings

* `$HOME/.config/spring-boot` könyvtárban <br /> pl. `spring-boot-devtools.properties` állomány

---

## Remote Applications

* El lehet indítani becsomagolt alkalmazáson is a DevToolst <br /> (`spring-boot-maven-plugin` konfigurálásával)
* Remote Client Applicationt kell elindítani, mely lokális gépen indul, <br /> és csatlakozik a távoli alkalmazáshoz
* Remote Update: ha valami változik a classpath-on, feltölti
* Ne használjuk éles környezetben

---

class: inverse, center, middle

# Twelve factor app

---

## Twelve-factor app

* [Twelve-factor app](https://12factor.net/) egy manifesztó, metodológia felhőbe <br /> telepíthető alkalmazások fejlesztésére
* Heroku platform fejlesztőinek ajánlása
* Előtérben a cloud, PaaS, continuous deployment
* PaaS: elrejti az infrastruktúra részleteit
    * Pl. Google App Engine, Redhat Open Shift, Pivotal Cloud Foundry, <br /> Heroku, AppHarbor, Amazon AWS, stb.

---

## Cloud native

* Jelző olyan szervezetekre, melyek képesek az automatizálás előnyeit kihasználva <br /> gyorsabban megbízható és skálázható alkalmazásokat szállítani
* Pivotal, többek között a Spring mögött álló cég
* Előtérben a continuous delivery, DevOps, microservices
* Alkalmazás jellemzői
    * PaaS-on fut (cloud)
    * Elastic: automatikus horizontális skálázódás

---

## Twelve-Factor app ajánlások - 1.

* Verziókezelés: "One codebase tracked in revision control, many deploys"
* Függőségek: "Explicitly declare and isolate dependencies"
* Konfiguráció: "Store config in the environment"
* Háttérszolgáltatások: "Treat backing services as attached resources"
* Build, release, futtatás: "Strictly separate build and run stages"
* Folyamatok: "Execute the app as one or more stateless processes"

---

## Twelve-Factor app ajánlások - 2.

* Port hozzárendelés: "Export services via port binding"
* Párhuzamosság: "Scale out via the process model"
* Disposability: "Maximize robustness with fast startup and graceful shutdown"
* Éles és fejlesztői környezet hasonlósága: <br /> "Keep development, staging, and production as similar as possible"
* Naplózás: "Treat logs as event streams"
* Felügyeleti folyamatok: <br /> "Run admin/management tasks as one-off processes"


---

## Beyond the Twelve-Factor App - 1.

* One Codebase, One Application
* API first
* Dependency Management
* Design, Build, Release, Run
* Configuration, Credentials and Code
* Logs
* Disposability
* Backing services

---

## Beyond the Twelve-Factor App - 2.

* Environment Parity
* Administrative Processes
* Port Binding
* Stateless Processes
* Concurrency
* Telemetry
* Authentication and Authorization

---

class: inverse, center, middle

# Bevezetés a Docker használatába

---

## Docker

* Operációs rendszer szintű virtualizáció
* Jól elkülönített környezetek, saját fájlrendszerrel és telepített szoftverekkel
* Jól meghatározott módon kommunikálhatnak egymással
* Kevésbé erőforrásigényes, mint a virtualizáció

---

## Megvalósítása

* Kliens - szerver architektúra, REST API
* Kernelt nem tartalmaz, hanem a host Linux kernel izoláltan futtatja
 * namespaces: operációs rendszer szintű elemek izolálására: folyamatok, InterProcess Communication (IPC), 
     fájlrendszer, hálózat, UTS (UNIX Timesharing System - host- és domainnév), felhasználók
 * cGroups (Control Groups): erőforrás limitáció
* Union FS (írásvédett, vagy írható/olvasható rétegek)

---

## Docker

<img src="images/container-what-is-container.png" alt="Docker" width="500" />

---

## Docker Windowson

* Docker Toolbox: VirtualBoxon futó Linuxon
* Docker Desktop
  * Hyper-V megoldás: LinuxKit, Linux Containers for Windows (LCOW), MobyVM
  * WSL2 - Windows Subsystem for Linux - 2-es verziótól Microsoft által Windowson fordított és futtatott Linux kernel

---

## Docker felhasználási módja

* Saját fejlesztői környezetben reprodukálható erőforrások
  * Adatbázis (relációs/NoSQL), cache, kapcsolódó rendszerek <br /> (kifejezetten microservice környezetben)
* Saját fejlesztői környezettől való izoláció
* Docker image tartalmazza a teljes környezetet, függőségeket is
* Portabilitás (különböző környezeten futattható, pl. saját gép, <br /> privát vagy publikus felhő)

---

## További Docker komponensek

* Docker Hub - publikus szolgáltatás image-ek megosztására
* Docker Compose - több konténer egyidejű kezelése
* Docker Swarm - natív cluster támogatás
* Docker Machine - távoli Docker környezetek üzemeltetéséhez

---

## Docker fogalmak

<img src="images/docker-containers.jpg" alt="Image és container" width="500" />

---

## Docker folyamat

* Alkalmazás
* Dockerfile
* Image
* Konténer

---

## Docker konténerek

```shell
docker version
docker run hello-world
docker run -p 8080:80 nginx
docker run -d -p 8080:80 nginx
docker ps
docker stop 517e15770697
docker run -d -p 8080:80 --name nginx nginx
docker stop nginx
docker ps -a
docker start nginx
docker logs -f nginx
docker stop nginx
docker rm nginx
```

Használható az azonosító első n karaktere is, amely egyedivé teszi

---

## Műveletek image-ekkel

```shell
docker images
docker rmi nginx
```

---

## Linux elindítása, bejelentkezés

```shell
docker run  --name myubuntu -d ubuntu tail -f /dev/null
docker exec -it myubuntu bash
```

---

class: inverse, center, middle

# Java alkalmazások Dockerrel

---

## Saját image összeállítása

`Dockerfile` fájl tartalma:

```dockerfile
FROM eclipse-temurin:17
WORKDIR app
COPY target/*.jar employees.jar
ENTRYPOINT ["java", "-jar", "employees.jar"]
```

Parancsok:

```shell
docker build -t employees .
docker run -d -p 8080:8080 employees
```

---

## docker-maven-plugin

* Fabric8
* Alternatíva: Spotify dockerfile-maven, Google [JIB Maven plugin](https://github.com/GoogleContainerTools/jib)

---

## Plugin

```xml
<plugin>
    <groupId>io.fabric8</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.32.0</version>
    <!-- ... -->    
</plugin>
```

---

## Plugin konfiguráció

.small-code-14[
```xml
<configuration>
    <verbose>true</verbose>
    <images>
        <image>
            <name>employees</name>
            <build>
                <dockerFileDir>${project.basedir}/src/main/docker/</dockerFileDir>
                <assembly>
                    <descriptorRef>artifact</descriptorRef>
                </assembly>
                <tags>
                    <tag>latest</tag>
                    <tag>${project.version}</tag>
                </tags>
            </build>
            <run>
                <ports>8080:8080</ports>
            </run>
        </image>
    </images>
</configuration>
```
]

---

## Dockerfile

```dockerfile
FROM eclipse-temurin:17
WORKDIR app
COPY maven/${project.artifactId}-${project.version}.jar employees.jar
ENTRYPOINT ["java", "-jar", "employees.jar"]
```

Property placeholder

---

## Parancsok

```shell
mvnw package docker:build
mvnw docker:start
mvnw docker:stop
```

A `docker:stop` törli is a konténert

---

## 12Factor hivatkozás: <br /> Disposability

* Nagyon gyorsan induljanak és álljanak le
* Graceful shutdown
* Ne legyen inkonzisztens adat
* Batch folyamatoknál: megszakíthatóvá, újraindíthatóvá (reentrant)
    * Tranzakciókezeléssel
    * Idempotencia


---

class: inverse, center, middle

# Docker layers

---

## Layers

<img src="images/docker-layers.png" alt="Docker layers" width="500"/>

`docker image inspect employees`

---

## Legjobb gyakorlat

* Külön változó részeket külön layerbe tenni
* Operációs rendszer, JDK, libraries, alkalmazás saját fejlesztésű része külön <br /> layerbe kerüljön

---

## Manuálisan

* Jar fájlt ki kell csomagolni, úgy is futtatható
  * `BOOT-INF/lib` - függőségek
  * `META-INF` - leíró állományok
  * `BOOT-INF/classes` - alkalmazás saját fájljai
  
```shell
java -cp BOOT-INF\classes;BOOT-INF\lib\* training.employees.EmployeesApplication
```  

---

## Dockerfile

* [Multi-stage build](https://docs.docker.com/develop/develop-images/multistage-build/)
  
```dockerfile
FROM eclipse-temurin:17 as builder
WORKDIR app
COPY target/*.jar employees.jar
RUN jar xvf employees.jar

FROM eclipse-temurin:17
WORKDIR app
COPY --from=builder app/BOOT-INF/lib lib
COPY --from=builder app/META-INF META-INF
COPY --from=builder app/BOOT-INF/classes classes

ENTRYPOINT ["java", "-cp", "classes:lib/*", \
            	"training.employees.EmployeesApplication"]
```

---

## Spring támogatás

* Spring 2.3.0.M2-től
    * [Bejelentés](https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1)
* Layered JAR-s
* Buildpacks

---

## Layered JAR-s

* A JAR felépítése legyen layered
* Ki kell csomagolni
* Létrehozni a Docker image-t

---

## Layered JAR

2.3.0.M2 verziónál, azóta ez a default

```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <configuration>
    <layers>
      <enabled>true</enabled>
    </layers>
  </configuration>
</plugin>
```

---

## Kicsomagolás

```shell
java -Djarmode=layertools -jar target/employees-0.0.1-SNAPSHOT.jar list

java -Djarmode=layertools -jar target/employees-0.0.1-SNAPSHOT.jar extract
```

---

## Dockerfile

```dockerfile
FROM eclipse-temurin:17 as builder
WORKDIR app
COPY target/*.jar employees.jar
RUN java -Djarmode=layertools -jar employees.jar extract

FROM eclipse-temurin:17
WORKDIR app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]
```

---

## Buildpacks

* Dockerfile-hoz képest magasabb absztrakciós szint (Cloud Foundry vagy Heroku)
* Image készítése közvetlen Maven-ből vagy Grade-ből
* Alapesetben Java 11, spring-boot-maven-plugin konfigurálandó <br /> `BP_JAVA_VERSION` értéke `13.0.2`

```shell
mvnw spring-boot:build-image
docker run -d -p 8080:8080 --name employees employees:0.0.1-SNAPSHOT
docker logs -f employees
```

---

## 12Factor hivatkozás: <br /> Dependencies

* Az alkalmazás nem függhet az őt futtató környezetre telepített <br /> semmilyen csomagtól
* Függőségeket explicit deklarálni kell
* Nem a függőségek közé soroljuk a háttérszolgáltatásokat, <br /> mint pl. adatbázis, mail szerver, cache szerver, stb.
* Docker és Maven/Gradle segít
* Egybe kell csomagolni a függőségekkel, <br /> hiszen a futtató környezetben szükség van rá
* Függőségek ritkábban változnak: Docker layers
* Vigyázni az ismételhetőségre: ne használjunk <br /> intervallumokat!

---

class: inverse, center, middle

# Feltöltés Git repository-ba

---

## Feltöltés Git repository-ba

* Új GitHub repository létrehozás a webes felületen

```shell
git init
git add .
git commit -m "First commit"
git remote add origin https://github.com/username/employees.git
git push -u origin master
```

---

## 12Factor hivatkozás: <br /> One Codebase, One Application

* Egy alkalmazás, egy repository
* A többi függőségként definiálandó
* Gyakori megsértés:
    * Modularizált fejlesztésnél tűnhet ez jó ötletnek a modulokat <br /> külön repository-ban tartani: nagyon megbonyolítja a buildet
    * Külön repository, de ugyanazon üzleti <br /> domainen dolgozó különböző alkalmazás darabok
    * Egy repository, különböző alkalmazások
* A különböző környezetekre telepített példányoknál <br /> alapvető igény, hogy tudjuk, <br /> hogy mely verzióból készült
  <br />(felületen, logban látható legyen)

---

## Alkalmazások és csapatok <br /> kapcsolata

* Figyelni a Conway törvényre:  "azok a szervezetek, amelyek <br /> rendszereket terveznek, ... kénytelenek olyan terveket
  készíteni, <br />amelyek saját kommunikációs struktúrájuk másolatai"
* Egy codebase, több team - ellentmond a microservice elképzelésnek
* Lehetséges megosztás:
    * Library - függőségként felvenni
    * Microservice

---

class: inverse, center, middle

# REST webszolgáltatások GET művelet

---

## RESTful webszolgáltatások tulajdonságai

* Roy Fielding: Architectural Styles and the Design of Network-based <br /> Software Architectures, 2000
* Representational state transfer
* Alkalmazás erőforrások gyűjteménye, <br /> melyeken CRUD műveleteket lehet végezni
* HTTP protokoll erőteljes használata: <br /> URI, metódusok, státuszkódok
* JSON formátum használata
* Egyszerűség, skálázhatóság, platformfüggetlenség
* [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

---

## Annotációk

* `@RestController`, mintha minden metóduson `@ResponseBody` annotáció
    * Alapértelmezetten JSON formátumba
* `@RequestMapping` annotation helyett `@GetMapping`, `@PostMapping`, stb.

---

## Controller

```java
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees() {
        return employeesService.listEmployees();
    }
}
```

---

## Architektúra

![Architektúra](images/rest-architecture.png)

---

## Lombok

* Boilerplate kódok generálására, pl. getter/setter, konstruktor, `toString()`, <br />equals/hashcode, logger, stb.
* Annotation processor
* IntelliJ IDEA támogatás: plugin és _Enable annotation processing_
* `@Data` annotáció
    * `@ToString`, `@EqualsAndHashCode`, `@Getter` minden attribútumon, <br />`@Setter` nem final attribútumon és `@RequiredArgsConstructor`
* `@NoArgsConstructor`

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
</dependency>
```

---

## Példa a Lombok használatára

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private long id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
```

---

## ModelMapper

* Object mapping
* Hasonló struktúrájú osztályú példányok konvertálására <br />(pl. entitások és DTO-k között)
* Reflection alapú, intelligens alapértékekkel
* Fluent mapping API speciális esetek kezelésére


```xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>${modelmapper.version}</version>
</dependency>
```

---

## Példa a ModelMapper <br /> használatára

```java
@Bean
public ModelMapper modelMapper() {
  return new ModelMapper();
}
```

```java
Employee employee = // load
EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);

List<Employee> employees = // load
java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
List<EmployeeDto> dtos = modelMapper.map(employees, targetListType);
```

---

class: inverse, center, middle

# GET műveletek paraméterezése

---

## URL paraméterek kezelése

* `@RequestParam` annotációval
* Kötelező, kivéve a `required = "false"` attribútum megadásakor
* Automatikus típuskonverzió


```java
public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
       return employeesService.listEmployees(prefix);
}
```

Elérhető a `/api/employees?prefix=Jack` címen

---

## Több URL paraméter kezelése

* Létrehozni egy objektumot
* Nem szükséges annotáció

```java
public List<EmployeeDto> listEmployees(QueryParameters parameters) {
   return employeesService.listEmployees(parameters);
}
```

```java
@Data
public class QueryParameters {
    private String prefix;

    private String postfix;
}
```

---

## URL részletek kezelése

* Osztályon lévő `@RequestMapping` és `@GetMapping` összeadódik

```java
@GetMapping("/{id}")
public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
    return employeesService.findEmployeeById(id);
}
```

Elérhető a `/api/employees/1` címen


---

## 12Factor hivatkozás: <br /> Stateless processes

* Csak egy kérés időtartamáig van állapot
  * Nem baj, ha elveszik
  * Nem kell cluster-ezni
  * Kevesebb erőforrás
  * Nincs párhuzamossági probléma
* Kérések közötti állapot: backing services
* Cache: inkább backing service, ne nőjön szignifikánsan <br /> az alkalmazás memóriaigénye
* Shared nothing: egy update csak egy node hatóköre

---

## Konkurrencia

* Ha állapotmentesen dolgozunk, nem okoz problémát
* Horizontális skálázás
* Backing service szintre kerül

---
class: inverse, center, middle

# REST webszolgáltatások POST és DELETE művelet

---

## POST és PUT művelet

* PUT idempotens

---

## Controller POST művelettel

* `@RequestBody` annotáció - deszerializáció, alapból JSON-ből Jacksonnel

```java
@PostMapping
public EmployeeDto createEmployee(
    @RequestBody CreateEmployeeCommand command) {
  return employeesService.createEmployee(command);
}

@PutMapping("/{id}")
public EmployeeDto updateEmployee(
    @PathVariable("id") long id,
    @RequestBody UpdateEmployeeCommand command) {
  return
    employeesService.updateEmployee(id, command);
}
```

---

## Controller DELETE művelettel

```java
@DeleteMapping("/{id}")
public void deleteEmployee(@PathVariable("id") long id) {
    employeesService.deleteEmployee(id);
}
```

---

class: inverse, center, middle

# REST legjobb gyakorlatok

---

## Legjobb gyakorlatok

* Használjunk mindig főneveket, amit az erőforrásokat reprezentálják
* Sose használjunk igéket
* Használjunk mindig többesszámot
* Használjunk kisbetűket és kötőjeleket elválasztásra
* Használjunk alerőforrásokat

---

## Példa URL-ek

* `/api/employees`
* ~~`/api/findEmployees`~~
* `/api/employees/1`
* ~~`/api/employees/create`~~
* ~~`/api/creeateEmployee`~~
* ~~`/api/employees/delete`~~
* ~~`/api/employees/close`~~
* `/api/stereo-receivers`
* `/api/employees/1/addresses/1`
* ~~`/api/employeeService/findEmployeeById`~~

---

class: inverse, center, middle

# Státuszkódok

---

## 204 - NO CONTENT státuszkód

```java
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteEmployee(@PathVariable("id") long id) {
    employeesService.deleteEmployee(id);
}
```

---

## Státuszkód állítása controller metódusból

* `ResponseEntity` visszatérési típus: státuszkód, header, body, stb.

```java
@GetMapping("/{id}")
public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") long id) {
    try {
        return ResponseEntity
                .ok()
                .header("Response-Id", UUID.randomUUID().toString())
                .body(employeesService.findEmployeeById(id));
    }
    catch (IllegalArgumentException iea) {
        return ResponseEntity
                .notFound()
                .header("Response-Id", UUID.randomUUID().toString())
                .build();
    }
}
```

---

## 201 - CREATED

* Location header megadása a HTTP szabvány szerint

```java
@PostMapping
public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeCommand command, 
        UriComponentsBuilder uri) {
    EmployeeDto employeeDto = employeeService.createEmployee(command);
    return ResponseEntity
        .created(uri.path("/api/employees/{id}").buildAndExpand(employeeDto.getId()).toUri())
        .body(employeeDto);
}
```

---

# Unit teszt

```java
@Test
void createEmployee() {
    EmployeeDto result = new EmployeeDto();
    result.setId(1L);
    result.setName("John Doe");
    when(employeesService.createEmployee(any())).thenReturn(result);

    CreateEmployeeCommand command = new CreateEmployeeCommand();
    command.setName("John Doe");
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    
    ResponseEntity<EmployeeDto> response = employeesController.createEmployee(command, builder);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(List.of("http://localhost:8080/api/employees/1"), 
      response.getHeaders().get("Location"));
    assertEquals("John Doe", response.getBody().getName());
}
```

---

class: inverse, center, middle

# Hibakezelés

---

## Alapértelmezett válasz hiba

* Status code 500

```json
{
  "timestamp": 1596570258672,
  "status": 500,
  "error": "Internal Server Error",
  "message": "Employee not found: 3",
  "path": "/api/employees/3"
}
```

---

## Hibakezelés

* Servlet szabvány szerint `web.xml` állományban
* `@ExceptionHandler` annotációval ellátott metódus a controllerben
* `@ControllerAdvice` annotációval ellátott globális `@ExceptionHandler` <br /> annotációval ellátott metódus
* Globálisan `ExceptionResolver` osztályokkal
* Exceptionre tehető `@ResponseStatus` annotáció

---

## ExceptionHandler

```java
@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public void handleNotFound() {    
}
```

---

## RFC 7807

* Problem Details for HTTP APIs
* `application/problem+json` mime-type

```json
{
  "type": "employees/employee-not-found",
  "title": "Not found",
  "status": 404,
  "detail": "Employee not found: 3",
  "instance": "/api/employees/3",
  "properties": {
    "id": "e7439e5b-6144-4009-82d1-ae51f184ef01"
  }
}
```

---

## RFC 7807 mezők

* `type`: URI, mely azonosítja a hiba típusát
* `title`: ember által olvasható üzenet
* `status`: http státuszkód
* `detail`: részletek, ember által olvasható
* `instance`: URI, mely azonosítja a hibát, <br />és később is elérhető (pl. valamilyen log hivatkozás)
* Egyedi saját mezők definiálhatók

---

## Alapértelmezett működés <br /> bekapcsolása

```
spring.mvc.problemdetails.enabled = true
```

Pl. formai hibás JSON beküldésekor

---

## `ExceptionHandler` használatával

```java
@ControllerAdvice
public class EmployeesExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ProblemDetail handleNotFoundException(EmployeeNotFoundException nfe) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                String.format(nfe.getMessage()));
        problemDetail.setType(URI.create("employees/employee-not-found"));
        problemDetail.setTitle("Not found");
        problemDetail.setProperty("id", UUID.randomUUID().toString());
        return problemDetail;
    }

}
```

---

class: inverse, center, middle

# Integrációs tesztelés

---

## Web réteg tesztelése

* Elindítható csak a Spring MVC réteg: <br />`@SpringBootTest` helyett `@WebMvcTest` annotáció használata
* Service réteg mockolható Mockitoval, `@MockBean` annotációval
* `MockMvc` injektálható
    * Kérések összeállítására (path variable, paraméterek, header, stb.)
    * Válasz ellenőrzésére (státuszkód, header, tartalom)
    * Válasz naplózására
    * Válasz akár Stringként, JSON dokumentumként <br />(jsonPath)
* Nem indít valódi konténert, a Servlet API-t mockolja
* JSON szerializáció

---

## Web réteg tesztelése példa

.small-code-14[
```java
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
```

```java
@Test
void testListEmployees() throws Exception {
    when(employeesService.listEmployees(any())).thenReturn(List.of(
            new EmployeeDto(1L, "John Doe"),
            new EmployeeDto(2L, "Jane Doe")
    ));

    mockMvc.perform(get("/api/employees"))
      .andDo(print())
            .andExpect(status().isOk())        
            .andExpect(jsonPath("$[0].name", equalTo("John Doe")));
}
```
]

---

## Teljes alkalmazás tesztelése <br /> konténer nélkül

* `@SpringBootTest` és `@AutoConfigureMockMvc` annotáció

```java
@Test
void testListEmployees() throws Exception {
  mockMvc.perform(get("/api/employees"))
    .andExpect(status().isOk())
    .andDo(print())
    .andExpect(
      jsonPath("$[0].name", equalTo("John Doe")));
}
```

---

## Teljes alkalmazás tesztelése <br /> konténerrel

* `RANDOM_PORT`
* Port `@LocalServerPort` annotációval injektálható
* Injektálható `TestRestTemplate` - url és port előre beállítva
* JSON szerializáció és deszerializáció

```java
@SpringBootTest(webEnvironment =
    SpringBootTest.WebEnvironment.RANDOM_PORT)
```

---

## Teljes alkalmazás tesztelése <br /> konténerrel példa

```java
@Test
void testListEmployees() {
  List<EmployeeDto> employees = 
    restTemplate.exchange("/api/employees",
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<EmployeeDto>>(){})
    .getBody();
  
  assertThat(employees)
          .extracting(EmployeeDto::getName)
          .containsExactly("John Doe", "Jane Doe");
}
```

---

class: inverse, center, middle

# Tesztelés WebClient használatával

---

## WebClient

* Spring Framework 5.0 vezette be alapvetően WebFlux integrációs tesztekhez, de működik Spring MVC-vel is
* Fluent API assertionök írásához
* Szükséges a `org.springframework.boot:spring-boot-starter-webflux` függőség
* Spring MVC esetén nem használható, csak valós konténerrel

---

## Egyszerű kérések és assert

* Metódus, uri, kérés törzse és státuszkód

```java
webClient.post().uri("/api/employees")
        .bodyValue(new CreateEmployeeCommand(("John Doe")))
        .exchange()
        .expectStatus().isCreated()
```

A `post()` mellett `get()`, `put()` és `delete()` metódusok

---

## URI

Path variable (URI variable)

```java
webClient.get().uri("/api/employees/{id}", 1)
```

Request parameter (query parameter)

```java
webClient.get().uri(builder -> builder.path("/api/employees").queryParam("prefix", "j").build())
```

Paraméterként `Function`

---

## Válasz értelmezése JSON-ként

```java
.expectBody(String.class).value(s -> System.out.println(s))
```

Paraméterként `Consumer`

JSON Path

```java
.expectBody().jsonPath("name").isEqualTo("John Doe");
```

---

## Egyszerű objektum és lista

```java
.expectBody(EmployeeDto.class).value(e -> assertEquals("John Doe", e.getName()));
```

Lista

```java
.expectBodyList(EmployeeDto.class).hasSize(2).contains(new EmployeeDto(1L, "John Doe"));
```

---

class: inverse, center, middle

# Swagger UI

---

## Swagger UI

* API dokumentáció generálására
* Az API ki is próbálható
* OpenAPI Specification (eredetileg Swagger Specification)
  * RESTful webszolgáltatások leírására
  * Kód és dokumentáció generálás
  * Programozási nyelv független
  * JSON/YAML formátumú
  * JSON Scheman alapul
* Keretrendszer független
* Annotációkkal személyre szabható

---

## Spring integráció

* Swagger UI automatikus elindítása a `/swagger-ui.html` címen
* OpenAPI elérhetőség a `/v3/api-docs` címen (vagy `/v3/api-docs.yaml`)

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.1.0</version>
</dependency>
```

---

## Globális testreszabás

```java
@Bean
public OpenAPI customOpenAPI() {
  return new OpenAPI()
  .info(new Info()
  .title("Employees API")
  .version("1.0.0")
  .description("Operations with employees"));
}
```

---

## Séma testreszabás

* Figyelembe veszi a Bean Validation annotációkat

```java
public class CreateEmployeeCommand {

    @Schema(description="name of the employee", example = "John Doe")
    private String name;
}
```

---

## Osztály és metódus szint

* Figyelembe veszi a Spring MVC annotációkat

```java
@RestController
@RequestMapping("/api/employees")
@Tag( name = "Operations on employees")
public class EmployeesController {

  @GetMapping("/{id}")
  @Operation(summary = "Find employee by id",
    description = "Find employee by id.")
  @ApiResponse(responseCode = "404",
    description = "Employee not found")
  public EmployeeDto findEmployeeById(
      @Parameter(description = "Id of the employee",
        example = "12")
      @PathVariable("id") long id) {
    // ...
  }

}
```

---

## 12Factor hivatkozás: API first

* Contract first alapjain
* Laza csatolás
* Webes és mobil GUI és az üzleti logika is ide tartozik
* Dokumentálva és tesztelve legyen
* [API Blueprint](https://apiblueprint.org/): Markdown alapú formátum API dokumentálására

---

class: inverse, center, middle

# Tesztelés REST Assured használatával

---

## REST Assured

* Keretrendszer független eszköz REST API tesztelésére
* Dinamikus nyelvek egyszerűségét próbálja hozni Java nyelven
* JSON, mint szöveg, vagy objektum mapping (Jackson, Gson, JAXB, stb.)
* Megadható
  * Path, parameter, header, cookie, content-type, stb.
* Sokszínű assertek
* Támogatja a különböző autentikációs módokat

---

## REST Assured - Assert

* XML tartalomra XmlPath, GPath (Groovy-ból, hasonló az XPath-hoz)
* DTD és XSD validáció
* JSON tartalomra JSONPath-szal
* JSON Schema validáció
* Header, status, cookie, content-type
* Response time


---

## Függőségek - JsonPath és XmlPath

```xml
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>json-path</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>xml-path</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
```

Csak JSON használata esetén is kell mindkét függőség

---

## Függőségek

* Un. RestAssuredMockMvc API

```xml
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>rest-assured</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>spring-mock-mvc</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
```

---

## Inicializálás

```java
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;
```

```java
@Autowired
WebApplicationContext webApplicationContext;

@BeforeEach
void init() {
  RestAssuredMockMvc.requestSpecification = given()
    .contentType(ContentType.JSON)
    .accept(ContentType.JSON);

  RestAssuredMockMvc
    .webAppContextSetup(webApplicationContext);
}
```

---

## Teszteset

```java
@Test
void testCreateEmployeeThenListEmployees() {
    with().body(new CreateEmployeeCommand("Jack Doe")).
    when()
      .post("/api/employees")
      .then()
      .body("name", equalTo("Jack Doe"));

    when()
      .get("/api/employees")
      .then()
      .body("[0].name", equalTo("Jack Doe"));
}
```

---

class: inverse, center, middle

# REST Assured séma validáció

---

## JSON Schema

```json
{
  "$schema": "https://json-schema.org/draft/2019-09/schema",
  "title": "Employees",
  "type": "array",
  "items": [
    {
      "title": "EmployeeDto",
      "type": "object",
      "required": ["name", "id"],
      "properties": {
        "id": {
          "type": "integer",
          "description": "id of the employee",
          "format": "int64",
          "example": 12
        },
        "name": {
          "type": "string",
          "description": "name of the employee",
          "example": "John Doe"
}}}]}
```
---

## Függőség

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <scope>test</scope>
</dependency>
```

---

## JSON Schema validáció

```java
when()
    .get("/api/employees")
    .then()
    .body(matchesJsonSchemaInClasspath("employee-dto-schema.json"));
```

---

class: inverse, center, middle

# Content negotiation

---

## Content negotiation

* Mechanizmus, mely lehetővé teszi a kliens számára, <br />hogy az erőforrás megjelenítési
  formái közül válasszon, pl.
    * JSON vagy XML (`Accept` fejléc és Mime Type)
    * GIF vagy JPEG
    * Nyelv (`Accept-Language` fejléc alapján)

---

## Content negotiation Spring Boot támogatás

* Controllerben

```java
@RequestMapping(value = "/api/employees",
  produces = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE})
```

* `pom.xml`-ben

```xml
<dependency>
  <groupId>org.glassfish.jaxb</groupId>
  <artifactId>jaxb-runtime</artifactId>
</dependency>
```

* Dto-ban `@XmlRootElement`

---

## Lista esetén

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesDto {

    @XmlElement(name = "employee")
    private List<EmployeeDto> employees;
}
```

---

class: inverse, center, middle

# Validáció

---

## Validáció

* Bean Validation 2.0 (JSR 380) támogatás
* Ne réteghez legyen kötve, hanem az adatot hordozó beanhez
* Osztályra vagy attribútumokra tehető annotációk
* Megadható metódus paraméterekre és visszatérési értékre is
* Beépített annotációk
* Saját annotáció implementálható
* Nyelvesített hibaüzenetek

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## Beépített annotációk 1.

* `@AssertFalse`, `@AssertTrue`
* `@Null`, `@NotNull`
* `@Size`
* `@Max`, `@Min`, `@Positive`, <br />`@PositiveOrZero`, `@Negative`, `@NegativeOrZero`
* `@DecimalMax`, `@DecimalMin`
* `@Digits`

---

## Beépített annotációk 2.


* `@Future`, `@Past`, <br />`@PastOrPresent`, `@FutureOrPresent`
* `@Pattern`
* `@Email`
* `@NotEmpty`, `@NotBlank`

---

## Validáció controlleren

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @NotNull(message = "Name can not be null")
    private String name;
}
```

```java
@PostMapping
public EmployeeDto createEmployee(
    @Valid @RequestBody CreateEmployeeCommand command) {
  return employeesService.createEmployee(command);
}
```

---

## Válasz

```json
{
  "timestamp": 1596570707472,
  "status": 400,
  "error": "Bad Request",
  "message": "",
  "path": "/api/employees"
}
```

---

class: inverse, center, middle

# Validáció Problem Details használatával

---

## Beépített Spring támogatás személyre szabásával

.small-code-14[
```java
@ExceptionHandler
public ProblemDetail handle(MethodArgumentNotValidException exception) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    problemDetail.setType(URI.create("employees/validation-error"));
    problemDetail.setTitle("Validation error");
    List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
            .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
            .toList();
    problemDetail.setProperty("violations", violations);
    problemDetail.setType(URI.create("employees/employee-not-found"));
    return problemDetail;
}
```
]

---

## Violation osztály

```java
@Data
@AllArgsConstructor
public class Violation {

    private String field;

    private String defaultMessage;
}
```

---

## Kapott válasz

```json
{
  "type": "employees/validation-error",
  "title": "Validation error",
  "status": 400,
  "detail": "Validation failed for argument [0] in public ...",
  "violations": [
    {
      "field": "name",
      "message": "Name can not be null"
    }
  ]
}
```

---

class: inverse, center, middle

# Saját validáció készítése

---

## Saját annotáció

```java
@Constraint(validatedBy = NameValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Name {

    String message() default "Invalid name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int maxLength() default 50;

}
```

---

## Validator osztály

```java
public class NameValidator implements ConstraintValidator<Name, String> {

  private int maxLength;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null &&
      !value.isBlank() &&
      value.length() > 2 && 
      value.length() <= maxLength && 
      Character.isUpperCase(value.charAt(0));
  }

  @Override
  public void initialize(Name constraintAnnotation) {
      maxLength = constraintAnnotation.maxLength();
  }
}
```

---

class: inverse, center, middle

# Spring Boot konfiguráció

---

## Externalized Configuration

* Konfiguráció alkalmazáson kívül szervezése, <br /> hogy ugyanazon alkalmazás több környezetben is tudjon futni
* Spring `Environment` absztrakcióra épül, `PropertySource` implementációk <br />hierarchiája,
  melyek különböző helyekről töltenek be property-ket
* Majdnem húsz forrása a property-knek<br />(a magasabb prioritásúak felülírják a később szereplőket)
* Leggyakoribb az `application.properties` fájl
* YAML formátum is használható

---

## Források

* Az elöl szereplők felülírják a később szereplőket
* Legfontosabbak:
  * Parancssori paraméterek
  * Operációs rendszer környezeti változók
  * `application.properties` állomány a jar fájlon kívül <br />(`/config` könyvtár, vagy közvetlenül a jar mellett)
  * `application.properties` állomány a jar fájlon belül

---

## Konfiguráció beolvasása @Value annotációval

```java
@Service
public class HelloService {

  private String hello;

  public HelloService(@Value("${employees.hello}") String hello) {
    this.hello = hello;
  }

  public String sayHello() {
    return hello + " " + LocalDateTime.now();
  }
}
```

---

## application.properties tartalma

```properties
employees.hello = Hello Spring Boot Config
```

---

## ConfigurationProperties

* Több, esetleg hierarchikus property-k esetén


```java
@ConfigurationProperties(prefix = "employees")
@Data
public class HelloProperties {

    private String hello;
}
```

* Regisztrálni kell a
`@EnableConfigurationProperties(HelloProperties.class)` annotációval, pl. a service-en
* Használat helyén injektálható

---

## További ConfigurationProperties lehetőségek

* Setteren keresztül, de használható a `@ConstructorBinding`, ekkor konstruktoron keresztül
* Relaxed binding: nem kell pontos egyezőség
* Használható a `@Validated` Spring annotáció, <br />(majd használható a Bean Validation)
* A property-ket definiálni lehet külön állományban, <br />ekkor felismeri az IDE

```
META-INF/additional-spring-configuration-metadata.json
```

---

## Előre definiált property-k

* Százas nagyságrendben
* Spring Boot Reference Documentation: <br />[Appendix A: Common Application properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#common-application-properties)

---

## Property-k titkosítása

* Pl. [Jasypt használatával](https://www.baeldung.com/spring-boot-jasypt)

---

## Port

* `server.port`

---

## 12Factor: Port binding

* Konfigurálható legyen a port, ahol elindul
* Két alkalmazás ne legyen telepítve ugyanarra a web konténerre, <br /> alkalmazásszerverre

---

## Konfiguráció Dockerrel

```shell
docker run -d -p 8080:8081 -e SERVER_PORT=8081 -e EMPLOYEES_HELLO=HelloDocker employees
```

---

## 12Factor: Configuration

* Környezetenként eltérő értékek
* Pl. backing service-ek elérhetőségei
* Ide tartoznak a jelszavak, titkos kulcsok, <br />melyeket különös figyelemmel kell kezelni
* Konfigurációs paraméterek a környezet részét képezzék, <br />és ne az alkalmazás részét
* Konfigurációs paraméterek környezeti változókból jöjjenek
* Kerüljük az alkalmazásban a környezetek nevesítését
* Nem kerülhetnek a kód mellé a verziókezelőbe <br />(csak a fejlesztőkörnyezet default beállításai)
* Verziókezelve legyen, ki, mikor mit módosított
* Lásd még Spring Cloud Config

---

class: inverse, center, middle

# Spring Boot naplózás

---

## Naplózás

* Spring belül a Commons Loggingot használja
* Előre be van konfigurálva a Java Util Logging, Log4J2, és Logback
* Alapesetben konzolra ír
* Naplózás szintje, és fájlba írás is állítható <br />az `application.properties` állományban

---

## Best practice

* SLF4J használata
* Lombok használata
* Paraméterezett üzenet

```java
private static final org.slf4j.Logger log =
  org.slf4j.LoggerFactory.getLogger(LogExample.class);
```

```java
@Slf4j
```

```java
log.info("Employee has been created");
log.debug("Employee has been created with name {}",
  employee.getName());
```

---

## Konfiguráció

* `application.properties`: szint, fájl
* Használható logger library specifikus konfigurációs fájl (pl. `logback.xml`)

```properties
logging.level.training = debug
```

---

## 12Factor hivatkozás: Naplózás

* Time ordered event stream
* Nem az alkalmazás feladata a napló irányítása a megfelelő helyre, <br />vagy a napló tárolása, kezelése, archiválása, görgetése, stb.
* Írjon konzolra
* Központi szolgáltatás: pl. ELK, Splunk, hiszen <br />az alkalmazás node-ok bármikor eltűnhetnek

---

class: inverse, center, middle

# Feature toggles

---

## Feature toggles

* Futásidőben funkciók be- és kikapcsolására
* Continuous integration miatt, feature branchek csökkentésére
  * Merge conflict minimalizálására
* Blue/green deployment támogatására: bekapcsolni csak ha minden node új verziót futtat
* Canary release: csak a felhasználók egy részének bekapcsolni
* Dark Launch: beérkező kérések csak egy százalékának bekapcsolni, figyelni a rendszer viselkedését
* A/B tesztelés: két különböző megvalósítás tesztelésére
* Circuit Breaker: problémát/terhelést okozó funkció kikapcsolása

---

## FF4J

* [FF4J](https://ff4j.org/) egy Javas Feature toggles implementáció
* Támogatja a szerepkör szerinti szétválasztást, akár Spring Security keretrendszerrel
* AOP támogatás
* Monitorozás
* Auditálható
* Parancssori, JMX, REST, webes interfész
* Választható adatbázis és cache implementációk
* Saját stratégiákkal bővíthető
* Spring Boot integráció

---

## Függőségek

```xml
<dependency>
  <groupId>org.ff4j</groupId>
  <artifactId>ff4j-spring-boot-starter</artifactId>
  <version>${ff4j.version}</version>
</dependency>
<dependency>
  <groupId>org.ff4j</groupId>
  <artifactId>ff4j-web</artifactId>
  <version>${ff4j.version}</version>
  <exclusions>
    <exclusion>
      <groupId>javax.servlet.jsp.jstl</groupId>
      <artifactId>jstl-api</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```

---

## Feature használata

```java
@Service
public class EmployeesService {

  private FF4j ff4j;
  
  @PostConstruct
    public void init() {
        ff4j.createFeature(new Feature(FEATURE_CHECK_UNIQUE));
    }
  
  public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        if (ff4j.check(FEATURE_CHECK_UNIQUE)) {
            // Kapcsolható funkció
        }
  
  // ...
  }		
}
```

---

## Feature kapcsolása

REST API

```plaintext
### Get feature

GET http://localhost:8080/api/ff4j/store/features/checkUnique

### Enable feature

POST http://localhost:8080/api/ff4j/store/features/checkUnique/enable
Content-Type: application/json

### Disable feature

POST http://localhost:8080/api/ff4j/store/features/checkUnique/disable
Content-Type: application/json
```

---

class: inverse, center, middle

# Spring JdbcTemplate

---

## Spring JdbcTemplate

* JDBC túl bőbeszédű
* Elavult kivételkezelés
  * Egy osztály, üzenet alapján megkülönböztethető
  * Checked
* Boilerplate kódok eliminálására template-ek
* Adatbáziskezelés SQL-lel

---

## Architektúra

![Architektúra](images/db-architecture.png)

---

## JDBC használata

* `org.springframework.boot:spring-boot-starter-jdbc` függőség
* Embedded adatbázis támogatás, automatikus `DataSource` konfiguráció
    * Pl H2: `com.h2database:h2`
    * Developer Tools esetén  elérhető webes konzol a `/h2-console` címen
* Injektálható `JdbcTemplate`
* Service delegál a Repository felé

---

## Insert, update és delete

```java
jdbcTemplate.update(
  "insert into employees(emp_name) values ('John Doe')");

jdbcTemplate.update(
  "insert into employees(emp_name) values (?)", "John Doe");

jdbcTemplate.update(
  "update employees set emp_name = ? where id = ?", "John Doe", 1);

jdbcTemplate.update(
  "delete from employees where id = ?", 1);
```

---

## Generált id lekérése

```java
KeyHolder keyHolder = new GeneratedKeyHolder();

jdbcTemplate.update(
  con -> {
      PreparedStatement ps =
        con.prepareStatement("insert into employees(emp_name) values (?)",
          Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, employee.getName());
      return ps;
  }, keyHolder);

employee.setId(keyHolder.getKey().longValue());
```

---

## Select

```java
List<Employee> employees = jdbcTemplate.query(
  "select id, emp_name from employees",
  this::convertEmployee);

Employee employee =
  jdbcTemplate.queryForObject(
    "select id, emp_name from employees where id = ?",
    this::convertEmployee,
    id);
```

```java
private Employee convertEmployee(ResultSet resultSet, int i)
    throws SQLException {
  long id = resultSet.getLong("id");
  String name = resultSet.getString("emp_name");
  Employee employee = new Employee(id, name);
  return employee;
}
```

---

## Séma inicializálás

.small-code-14[
```java
@Component
@AllArgsConstructor
public class DbInitializer implements CommandLineRunner {

  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    jdbcTemplate.execute("create table employees " +
      "(id bigint auto_increment, emp_name varchar(255), " +
      "primary key (id))");

    jdbcTemplate.execute(
      "insert into employees(emp_name) values ('John Doe')");
    jdbcTemplate.execute(
      "insert into employees(emp_name) values ('Jack Doe')");
  }
}
```
]

---

class: inverse, center, middle

# Spring Data JPA

---

## Spring Data JPA

* Egyszerűbbé teszi a perzisztens réteg implementálását
* Tipikusan CRUD műveletek támogatására, olyan gyakori igények <br />megvalósításával, mint a rendezés és a lapozás
* Interfész alapján repository implementáció generálás
* Query by example
* Ismétlődő fejlesztési feladatok redukálása, *boilerplate* kódok csökkentése

---

## Spring Data JPA használatba <br /> vétele

* `org.springframework.boot:spring-boot-starter-data-jpa` függőség
* Entitás létrehozása
* `JpaRepository` kiterjesztése
* `@Transactional` alkalmazása a service rétegben
* `application.properties`

```properties
spring.jpa.show-sql=true
```

---

## JpaRepository

* `save(S)`, `saveAll(Iterable<S>)`, `saveAndFlush(S)`
* `findById(Long)`, `findOne(Example<S>)`, <br />`findAll()` különböző paraméterezésű metódusai (lapozás, `Example`), <br />`findAllById(Iterable<ID>)`
* `getOne(ID)` (nem `Optional` példánnyal tér vissza)
* `exists(Example<S>)`, `existsById(ID)`
* `count()`, `count(Example<S>)`
* `deleteById(ID)`, `delete(S)`, <br /> `deleteAll()` üres és `Iterable` paraméterezéssel,
  <br />`deleteAllInBatch()`, <br />`deleteInBatch(Iterable<S>)`
* `flush()`

---

## Entitás

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "emp_name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
```

---

## Repository

```java
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where upper(e.name) like upper(:name)")
    List<Employee> findAllByPrefix(String name);

}
```

---

class: inverse, center, middle

# MariaDB

---

## MariaDB indítása Dockerrel

```shell
docker run 
  -d
  -e MYSQL_DATABASE=employees
  -e MYSQL_USER=employees
  -e MYSQL_PASSWORD=employees
  -e MYSQL_ALLOW_EMPTY_PASSWORD=yes
  -p 3306:3306
  --name employees-mariadb
  mariadb
```

---

## Driver

`pom.xml`-be:

```xml
<dependency>
  <groupId>org.mariadb.jdbc</groupId>
  <artifactId>mariadb-java-client</artifactId>
  <scope>runtime</scope>
</dependency>
```

---

## Inicializálás

* `application.properties` konfiguráció

```properties
spring.datasource.url=jdbc:mariadb://localhost/employees
spring.datasource.username=employees
spring.datasource.password=employees

spring.jpa.hibernate.ddl-auto=create-drop
```

---

## 12Factor: Backing services

* Adatbázis (akár relációs, akár NoSQL), üzenetküldő middleware-ek, <br /> directory és email szerverek, elosztott cache, Big Data eszközök, stb.
* Microservice környezetben egy másik alkalmazás is
* Automatizált telepítés
* Infrastructure as Code, Ansible, Chef, Puppet
* Eléréseik, autentikációs paraméterek környezeti paraméterként <br /> publikálódnak az alkalmazás felé
* Fájlrendszer nem tekinthető megfelelő <br /> háttérszolgáltatásnak
* Beágyazható háttérszolgáltatások
* Redeploy nélkül megoldható legyen a kapcsolódás
* Circuit breaker: ha nem működik a szolgáltatás, <br /> megszűnteti egy időre a hozzáférést (biztosíték)

---

class: inverse, center, middle

# PostgreSQL

---

## PostgreSQL indítása Dockerrel

```shell
docker run
  -d
  -e POSTGRES_PASSWORD=password 
  -p 5432:5432 
  --name employees-postgres 
  postgres
```

---

## Driver

`pom.xml`-be:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

---

## Inicializálás

* `application.properties` konfiguráció

```properties
spring.datasource.url=jdbc:postgresql:postgres
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=create-drop
```

---

class: inverse, center, middle

# Integrációs tesztelés

---

## JPA repository tesztelése

* JPA repository-k tesztelésére
* `@DataJpaTest` annotáció, csak a repository réteget indítja el
    * Embedded adatbázis
    * Tesztbe injektálható: JPA repository,  `DataSource`, `JdbcTemplate`, <br /> `EntityManager`
* Minden teszt metódus saját tranzakcióban, végén rollback
* Service réteg már nem kerül elindításra
* Tesztelni:
    * Entitáson lévő annotációkat
    * Névkonvenció alapján generált metódusokat
    * Custom query

---

## DataJpaTest

```java
@DataJpaTest
public class EmployeesRepositoryIT {

  @Autowired
  EmployeesRepository employeesRepository;

  @Test
  void testPersist() {
    Employee employee = new Employee("John Doe");
    employeesRepository.save(employee);
    List<Employee> employees =
      employeesRepository.findAllByPrefix("%");
    assertThat(employees)
      .extracting(Employee::getName)
      .containsExactly("John Doe");
  }

}
```

---

## @SpringBootTest használata

* Teljes alkalmazás tesztelése
* Valós adatbázis szükséges hozzá, gondoskodni kell az elindításáról
* Séma inicializáció és adatfeltöltés szükséges

---

## Tesztek H2 adatbázisban

* `src\test\resources\application.properties` fájlban <br /> a teszteléshez használt DataSource

```properties
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=sa
```

---

## Séma inicializáció

* `spring.jpa.hibernate.ddl-auto` `create-drop` alapesetben, <br /> teszt lefutása végén eldobja a sémát
    * `create`-re állítva megmaradnak a táblák és adatok
* Ha van `schema.sql` a classpath-on, azt futtatja le
* Flyway vagy Liquibase használata

---

## Adatfeltöltés

* `data.sql` a classpath-on
* `@Sql` annotáció használata a teszten
* Programozott módon
    * Teszt osztályban `@BeforeEach` vagy `@AfterEach` <br /> annotációkkal megjelölt metódusokban
    * Publikus API-n keresztül
    * Injektált controller, service, repository, stb. használatával
    * Közvetlen hozzáférés az adatbázishoz <br /> (pl. `JdbcTemplate`)

---

## Tesztek egymásra hatása

* Csak külön adatokon dolgozunk - nehéz lehet a kivitelezése
* Teszteset maga előtt vagy után rendet tesz
* Állapot
    * Teljes séma törlése, séma inicializáció
    * Adatbázis import
    * Csak (bizonyos) táblák ürítése

---

class: inverse, center, middle

# Alkalmazás futtatása Dockerben MariaDB-vel

---

## Architektúra

![Alkalmazás futtatása Dockerben](images/docker-mysql-arch.png)

---

## Hálózat létrehozása

```shell
docker network ls
docker network create --driver bridge employees-net
docker network inspect employees-net
```

---

## Alkalmazás futtatása Dockerben

```shell
docker run
    -d  
*    -e SPRING_DATASOURCE_URL=jdbc:mariadb://employees-mariadb/employees
*    -e SPRING_DATASOURCE_USERNAME=employees
*    -e SPRING_DATASOURCE_PASSWORD=employees
    -p 8080:8080
*    --network employees-net
    --name employees
    employees
```

---

class: inverse, center, middle

# Alkalmazás futtatása Dockerben MariaDB-vel Fabric8 Docker Maven Pluginnel

---

## Adatbázis

```xml
<image>
  <name>mariadb</name>
  <alias>employees-mariadb</alias>
  <run>
  <env>
  <MYSQL_DATABASE>employees</MYSQL_DATABASE>
  <MYSQL_USER>employees</MYSQL_USER>
  <MYSQL_PASSWORD>employees</MYSQL_PASSWORD>
  <MYSQL_ALLOW_EMPTY_PASSWORD>yes</MYSQL_ALLOW_EMPTY_PASSWORD>
  </env>
  <ports>3306:3306</ports>
  </run>
</image>
```

---

## Wait

```
FROM adoptopenjdk:14-jre-hotspot
RUN  apt update \
     && apt-get install wget \
     && apt-get install -y netcat \
     && wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
     && chmod +x ./wait-for-it.sh
RUN mkdir /opt/app
ADD maven/${project.artifactId}-${project.version}.jar /opt/app/employees.jar
CMD ["./wait-for-it.sh", "-t", "180", "employees-mariadb:3306", "--", "java", "-jar", "/opt/app/employees.jar"]
```

---

## Alkalmazás

```xml
<image>

  <!--- ... -->

  <run>
  <env>
  <SPRING_DATASOURCE_URL>jdbc:mariadb://employees-mariadb/employees</SPRING_DATASOURCE_URL>
  </env>
  <ports>8080:8080</ports>
  <links>
  <link>employees-mariadb:employees-mariadb</link>
  </links>
  <dependsOn>
  <container>employees-mariadb</container>
  </dependsOn>
  </run>
</image>
```

---

class: inverse, center, middle

# Teljes alkalmazás futtatása docker compose-zal

---

## docker-compose.yml

```yaml
version: '3'

services:
  employees-mariadb:
    image: mariadb
    restart: always
    ports:
      - '3306:3306'
    environment:
      MYSQL_DATABASE: employees
      MYSQL_ALLOW_EMPTY_PASSWORD: 'yes' # aposztrófok nélkül boolean true-ként értelmezi
      MYSQL_USER: employees
      MYSQL_PASSWORD: employees

```

---

## docker-compose.yml folytatás

```yaml
  employees-app:
    image: employees
    ports:
      - "8080:8080"
    restart: always
    depends_on:
      - employees-mariadb
    environment:
      SPRING_DATASOURCE_URL: 'jdbc:mariadb://employees-mariadb:3306/employees'
    command: ["./wait-for-it.sh", "-t", "120", "employees-mariadb:3306", "--", "java", "-jar", "/opt/app/employees.jar"]
```

---

class: inverse, center, middle

# Integrációs tesztelés adatbázis előkészítéssel

---

## pom.xml

```xml
<profile>
  <id>startdb</id>
  <properties>
  <docker.filter>mariadb</docker.filter>
  </properties>
  <build>
  <plugins>
  <plugin>
  <groupId>io.fabric8</groupId>
  <artifactId>docker-maven-plugin</artifactId>
  <executions>
  <execution>
  <id>start</id>
  <phase>pre-integration-test</phase>
  <goals>
  <goal>start</goal>
  </goals>
  </execution>
  <execution>
  <id>stop</id>
  <phase>post-integration-test</phase>
  <goals>
  <goal>stop</goal>
  </goals>
  </execution>
  </executions>
  </plugin>
  </plugins>
  </build>
</profile>
```

---

class: inverse, center, middle

# Séma inicializálás Flyway-jel

---

## Séma inicializálás

* Adatbázis séma létrehozása (táblák, stb.)
* Változások megadása
* Metadata table alapján  

---

## Elvárások

* SQL/XML leírás
* Platform függetlenség
* Lightweight
* Visszaállás korábbi verzióra
* Indítás paranccssorból, alkalmazásból
* Cluster támogatás
* Placeholder támogatás
* Modularizáció
* Több séma támogatása

---

## Flyway függőség

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
```

Hibernate séma inicializálás kikapcsolás az
`application.properties` állományban:

```properties
spring.jpa.hibernate.ddl-auto=none
```

---

## Migration MariaDB esetén

`src/resources/db/migration/V1__employees.sql` állomány

```sql
create table employees (id bigint auto_increment,
  emp_name varchar(255), primary key (id));

insert into employees (emp_name) values ('John Doe');
insert into employees (emp_name) values ('Jack Doe');
```

`flyway_schema_history` tábla

---

## Migration PostgreSQL esetén

`src/resources/db/migration/V1__employees.sql` állomány

```sql
create table employees (id int8 generated by default as identity, 
  emp_name varchar(255), primary key (id));

insert into employees (emp_name) values ('John Doe');
insert into employees (emp_name) values ('Jack Doe');
```

`flyway_schema_history` tábla

---

class: inverse, center, middle

# Séma inicializálás Liquibase-zel

---

## Liquibase

`pom.xml`-ben

```xml
<dependency>
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-core</artifactId>
</dependency>
```

`application.properties` állományban

```properties
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

---

## Change log

A `db.changelog-master.xml` fájl:

```xml
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog 
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd
      http://www.liquibase.org/xml/ns/dbchangelog-ext 
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">

  <changeSet id="create-employee-table" author="vicziani">
    <sqlFile path="create-employee-table.sql"
      relativeToChangelogFile="true" />
  </changeSet>
</databaseChangeLog>
```

---

## SQL migráció

`create-employee-table.sql` fájl MariaDB esetén:

```sql
create table employees (id bigint not null auto_increment, emp_name varchar(255), primary key (id));
```

`create-employee-table.sql` fájl PostgreSQL esetén:

```sql
create table employees (id int8 generated by default as identity, emp_name varchar(255), 
  primary key (id));
```