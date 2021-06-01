package dk.database.server;

import dk.database.server.domain.UserCreation;
import dk.database.server.facade.DataFacadeImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase  {

    private GenericContainer postgresContainer;

    protected DataFacadeImpl dfi;

    private static String pathToFile = System.getProperty("user.home");
    private String host = "localhost";
    private int port = 5439;

    private void setupContainer() {
        postgresContainer = new GenericContainer(DockerImageName.parse("postgres:alpine"))
                .withExposedPorts(5432).withEnv("POSTGRES_PASSWORD", "postgres");
        postgresContainer.start();
        postgresContainer.withFileSystemBind(pathToFile+"Documents/database/assigments/DBD_Exam/aplication/server/src/test/resources/01_dbd_tables.sql","/docker-entrypoint-initdb.d/01_dbd_tables.sql");
        System.out.println(pathToFile);
        host = postgresContainer.getHost();
        port = postgresContainer.getFirstMappedPort();

    }

    public Connection setupConnection( String password) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        String URL = "jdbc:postgresql://localhost:" + port + "/postgres";
        String USER = "postgres";
        String PASSWORD = password;

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    @BeforeAll
    public void setUp() {
        host = "localhost";
        port = 5439;

        setupContainer();

        dfi = new DataFacadeImpl();
    }

    @AfterAll
    public void afterAll() {postgresContainer.stop();}

    protected UserCreation getJoerg() {return new UserCreation("Joerg", "joerg@mail.com", "joerg123");}
    protected UserCreation getMorten() {return new UserCreation("Morten", "morten@mail.com", "morten123");}
    protected UserCreation getClaus() {return new UserCreation("Claus", "claus@mail.com", "claus123");}
    protected UserCreation getMads() {return new UserCreation("Mads", "mads@mail.com", "mads123");}

}
