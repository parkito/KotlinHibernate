import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.lang.Class.forName;
import static java.sql.DriverManager.getConnection;
import static org.dbunit.dataset.xml.XmlDataSet.write;

/**
 * @author Artem Karnov @date 20.04.2017.
 *         artem.karnov@t-systems.com
 */
public class DataSetGenerator {
    private static Logger logger = LogManager.getLogger(DataSetGenerator.class);

    @Autowired
    private Environment environment;

    private List<String> tables;
    private IDatabaseConnection connection;
    Properties props;

    public DataSetGenerator() throws IOException {
        props = new Properties();
        props.load(DataSetGenerator.class.getClassLoader().getResourceAsStream("application.properties"));
    }

    public void createDataSet(List<String> tables) {
        this.tables = tables;
        setUpConnection();
        generate();
    }

    private void createDataSet() {
        setUpConnection();
        generate();
    }

    private void setUpConnection() {
        Connection jdbcConnection;
        try {
            String dbURL = props.getProperty("jdbc.url");
            forName(dbURL);
            jdbcConnection = getConnection(environment.getRequiredProperty("jdbc.url"));
            connection = new DatabaseConnection(jdbcConnection);
//            forName(props.getProperty("jdbc.driverClassName"));
//            String dbUser = props.getProperty("jdbc.url");
//            String dbPass = props.getProperty("jdbc.url");
            // TODO: 27.04.2017 finish it
//            connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch (Exception e) {
            logger.error("Create connection fail, message: %s", e);
        }
    }

    private void generate() {
        if (tables == null) {
            createDefaultTablesDataSet();
        } else {
            createTablesDataSet();
        }
    }


    private void createDefaultTablesDataSet() {
        try {
            tables = props
                    .stringPropertyNames()
                    .stream()
                    .filter(key -> key.startsWith("dbunit.dataset."))
                    .map(props::getProperty)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Can't create dataset ", ex);
        }

        createTablesDataSet();
    }

    private void createTablesDataSet() {
        for (String table : tables) {
            createTableDataSet(table);
        }
    }

    private void createTableDataSet(String table) {
        try {
            QueryDataSet queryDataSet = new QueryDataSet(connection);
            queryDataSet.addTable(table, "select * from " + table);
            write(queryDataSet, new FileOutputStream("dataSets/" + table + ".xml"));
        } catch (Exception e) {
            logger.error("Create Xml Fail, message: ", e.getMessage());
        }
    }

    private void createFile() throws FileNotFoundException {
        System.out.println(System.class.getResource("/").getPath());
        String dir = getClass().getResource("/").getPath();
        System.out.println(dir);
        OutputStream os;
        os = new FileOutputStream(dir + "/file.txt");
    }

    public static void main(String[] args) throws IOException {
        DataSetGenerator da = new DataSetGenerator();
        da.createDataSet();
    }
}