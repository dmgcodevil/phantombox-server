package com.git.server.storage.mongo.utils;

import com.mongodb.DB;
import com.mongodb.Mongo;
import junit.framework.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.annotation.PostConstruct;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Embedded mongodb factory.
 * <p/>
 * User: dmgcodevil
 * Date: 11/24/12
 * Time: 7:46 AM
 */
@Component
public class EmbeddedMongoDbFactory implements MongoDbFactory {

    private String databaseName;

    private String databaseHost;

    private int databasePort;

    private MongodProcess mongod;

    private Mongo mongo;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedMongoDbFactory.class);

    /**
     * Gets database name.
     *
     * @return database name
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Sets database name.
     *
     * @param databaseName database name
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * get database host.
     *
     * @return database host
     */
    public String getDatabaseHost() {
        return databaseHost;
    }

    /**
     * Set database host.
     *
     * @param databaseHost database host
     */
    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    /**
     * Gets database port.
     *
     * @return database port
     */
    public int getDatabasePort() {
        return databasePort;
    }

    /**
     * Sets database port.
     *
     * @param databasePort database port
     */
    public void setDatabasePort(int databasePort) {
        this.databasePort = databasePort;
    }

    /**
     * Post constructor.
     *
     * @throws IOException {@link IOException}
     */
    @PostConstruct
    public void init() throws IOException {
        Assert.assertNotNull(databasePort);
        Assert.assertNotNull(databaseHost);
        LOGGER.info("create mongod starter...");
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        LOGGER.info("create mongod executable...");
        MongodExecutable mongodExe = runtime.prepare(new MongodConfig(Version.Main.V2_2,
            databasePort, Network.localhostIsIPv6()));
        mongod = mongodExe.start();
        mongo = new Mongo(databaseHost, databasePort);
        LOGGER.info("Embedded mongo db factory was created.");
    }

    /**
     * Creates db .
     *
     * @return {@link DB}
     * @throws {@link DataAccessException}
     */
    @Override
    public DB getDb() throws DataAccessException {
        Assert.assertNotNull(databaseName);
        return mongo.getDB(databaseName);
    }

    /**
     * Creates db with defined db name.
     *
     * @param dbName db name
     * @return {@link DB}
     * @throws {@link DataAccessException}
     */
    @Override
    public DB getDb(String dbName) throws DataAccessException {
        Assert.assertNotNull(dbName);
        return mongo.getDB(dbName);
    }
}
