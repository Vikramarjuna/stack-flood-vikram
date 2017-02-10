package com.cisco.cmad.stackflood.util;

import static java.lang.String.format;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.cisco.cmad.stackflood.model.mongod.BaseMongoDO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class MongoConnection {

	private static final Logger LOGGER = Logger.getLogger(MongoConnection.class);
	
	private static final String MONGO_CLIENT_URI = PropertiesUtil.get("sf.db.mongodb.client.uri");
	private static final Integer CONNECTION_POOL_SIZE = Integer.parseInt(PropertiesUtil.get("sf.db.mongodb.connection.pool.size"));
	private static final Integer CONNECTION_IDLE_TIME = Integer.parseInt(PropertiesUtil.get("sf.db.mongodb.connection.idle.time"));
	private static final Integer CONNECTION_LIFE_TIME = Integer.parseInt(PropertiesUtil.get("sf.db.mongodb.connection.life.time"));
	private static final String DATABASE_NAME = PropertiesUtil.get("sf.db.mongodb.database.name");
	
	private static MongoConnection instance = new MongoConnection();
	
	private MongoClient mongo = null;
	private Datastore dataStore = null;
	private Morphia morphia = null;

	private MongoConnection() {}
	
	public MongoClient getMongo() throws RuntimeException {
		if (mongo == null) {
			LOGGER.debug("Starting Mongo");
			MongoClientOptions.Builder options = MongoClientOptions.builder()
													.connectionsPerHost(CONNECTION_POOL_SIZE)
													.maxConnectionIdleTime((CONNECTION_IDLE_TIME))
													.maxConnectionLifeTime((CONNECTION_LIFE_TIME));
													;

			MongoClientURI uri = new MongoClientURI(MONGO_CLIENT_URI, options);
			
			LOGGER.info("About to connect to MongoDB @ " + uri.toString());
			
			try {
				mongo = new MongoClient(uri);
			} catch (MongoException ex) {
				LOGGER.error("An error occoured when connecting to MongoDB", ex);
			} catch (Exception ex) {
				LOGGER.error("An error occoured when connecting to MongoDB", ex);
			}
		}

		return mongo;
	}

	public Morphia getMorphia() {
		if (morphia == null) {
			LOGGER.debug("Starting Morphia");
			morphia = new Morphia();

			LOGGER.debug(format("Mapping packages for clases within %s", BaseMongoDO.class.getName()));
			morphia.mapPackageFromClass(BaseMongoDO.class);
		}

		return morphia;
	}

	public Datastore getDatastore() {
		if (dataStore == null) {
			String dbName = DATABASE_NAME;
			LOGGER.debug(format("Starting DataStore on DB: %s", dbName));
			dataStore = getMorphia().createDatastore(getMongo(), dbName);
		}

		return dataStore;
	}

	public void init() {
		LOGGER.debug("Bootstraping Mongo DB and Morphia");
		getMongo();
		getMorphia();
		getDatastore();
	}
	
	public void close() {
		LOGGER.info("Closing MongoDB connection");
		if (mongo != null) {
			try {
				mongo.close();
				LOGGER.debug("Nulling the connection dependency objects");
				mongo = null;
				morphia = null;
				dataStore = null;
			} catch (Exception e) {
				LOGGER.error(format("An error occurred when closing the MongoDB connection\n%s", e.getMessage()));
			}
		} else {
			LOGGER.warn("mongo object was null, wouldn't close connection");
		}
	}
	
	public static MongoConnection getInstance() {
		return instance;
	}
}
