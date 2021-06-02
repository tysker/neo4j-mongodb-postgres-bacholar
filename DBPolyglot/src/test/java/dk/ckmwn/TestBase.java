package dk.ckmwn;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import dk.ckmwn.contract.ArticleManagement;
import dk.ckmwn.contract.KeywordManagement;
import dk.ckmwn.contract.StockManagement;
import dk.ckmwn.impl.ArticleManagementImpl;
import org.junit.jupiter.api.*;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    private GenericContainer mongoContainer, neoContainer;
    protected MongoClient mongoClient;
    protected Driver neoDriver;

    protected ArticleManagement am;
    protected KeywordManagement km;
    protected StockManagement sm;

    private String mongoHost = "localhost";
    private String neoHost = "localhost";
    private int mongoPort = 27017;
    private int neoPort = 7474;

    private void setupContainer() {
        mongoContainer = new GenericContainer(DockerImageName.parse("mongo:latest"))
                .withExposedPorts(mongoPort);
        neoContainer = new GenericContainer(DockerImageName.parse("neo4j:enterprise"))
                .withEnv("NEO4J_ACCEPT_LICENSE_AGREEMENT", "yes")
                .withEnv("NEO4JLABS_PLUGINS", "[\"graph-data-science\"]")
                .withEnv("NEO4J_AUTH", "none")
                .withExposedPorts(neoPort)
        .withStartupTimeout(Duration.ofMinutes(10L));

        mongoContainer.start();
        neoContainer.start();
        mongoHost = mongoContainer.getHost();
        mongoPort = mongoContainer.getFirstMappedPort();
        neoHost = neoContainer.getHost();
        neoPort = neoContainer.getMappedPort(7687);
    }

    @BeforeAll
    public void setup(){
//        host = "localhost";
//        port = 6379;

        /*
        The following line starts a new container with redis, and runs integration tests on it.

        Depending on your setup, it might not work. If it doesn't work, try this in your terminal:
        docker pull testcontainers/ryuk:0.3.0

        If it still doesn't work, you can comment out the line,
        BUT then the integration tests will be run against your local redis, AND IT WILL FLUSH DB 9!
        To prove that you have read this warning, delete the exception below.
        */
        setupContainer();
        mongoClient = new MongoClient(mongoHost, mongoPort);
        neoDriver = GraphDatabase.driver("bolt://"+neoHost+":"+neoPort, AuthTokens.basic("neo4j", "neo4j"));
//        MongoDatabase db = mongoClient.getDatabase("abc");
//        db.createCollection("articles");
//        am = new ArticleManagementImpl(db.getCollection("articles"), null);



//        jedis = new Jedis(host, port);
//        jedis.select(9);
//        time = new TimeFake();
//
//        um = new UserManagementImpl(jedis);
//        pm = new PostManagementImpl(jedis, time);

        //throw new RuntimeException("Read the warning above");
    }

//    public static void main(String[] args) {
//        new TestBase().setup();
//    }

//    @BeforeEach
//    public void beforeEach() {
//        jedis.flushDB();
//    }
//
//    protected UserCreation getAlbert() {
//        return new UserCreation("ahr","Albert", "Rumle", "aou87", "1951-03-03");
//    }
//
//    protected UserCreation getBenny() {
//        return new UserCreation("ben","Benny", "Juuel", "htiuh9", "1941-04-04");
//    }
//
//    protected UserCreation getCarl() {
//        return new UserCreation("crl","Carl", "Vladimirovich", "tytytt", "1981-05-05");
//    }
//
//    protected UserCreation getDennis() {
//        return new UserCreation("den","Dennis", "Olsen", "uhcah834", "1982-06-06");
//    }
}

