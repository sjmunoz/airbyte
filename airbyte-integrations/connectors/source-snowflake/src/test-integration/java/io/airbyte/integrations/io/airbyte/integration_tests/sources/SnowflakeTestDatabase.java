package io.airbyte.integrations.io.airbyte.integration_tests.sources;

import com.fasterxml.jackson.databind.JsonNode;
import io.airbyte.cdk.db.factory.DatabaseDriver;
import io.airbyte.cdk.db.jdbc.JdbcUtils;
import io.airbyte.cdk.testutils.NonContainer;
import io.airbyte.cdk.testutils.TestDatabase;
import org.jooq.SQLDialect;

import java.util.stream.Stream;

import static io.airbyte.cdk.db.factory.DatabaseDriver.SNOWFLAKE;

public class SnowflakeTestDatabase extends TestDatabase<NonContainer, SnowflakeTestDatabase, SnowflakeTestDatabase.SnowflakeConfigBuilder> {
    private final String username;
    private final String password;
    private final String jdbcUrl;

    protected SnowflakeTestDatabase(final JsonNode snowflakeConfig) {
        super(new NonContainer(snowflakeConfig.get(JdbcUtils.USERNAME_KEY).asText(),
                snowflakeConfig.has(JdbcUtils.PASSWORD_KEY) ? snowflakeConfig.get(JdbcUtils.PASSWORD_KEY).asText() : null,
                snowflakeConfig.get(JdbcUtils.JDBC_URL_KEY).asText()
                , ""));
        this.username = snowflakeConfig.get(JdbcUtils.USERNAME_KEY).asText();
        this.password =  snowflakeConfig.has(JdbcUtils.PASSWORD_KEY) ? snowflakeConfig.get(JdbcUtils.PASSWORD_KEY).asText() : null;
        this.jdbcUrl =  snowflakeConfig.get(JdbcUtils.JDBC_URL_KEY).asText();
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    protected Stream<Stream<String>> inContainerBootstrapCmd() {
        return Stream.empty();
    }

    @Override
    protected Stream<String> inContainerUndoBootstrapCmd() {
        return Stream.empty();
    }

    @Override
    public DatabaseDriver getDatabaseDriver() {
        return SNOWFLAKE;
    }

    @Override
    public SQLDialect getSqlDialect() {
        return SQLDialect.DEFAULT;
    }

    static public class SnowflakeConfigBuilder extends TestDatabase.ConfigBuilder<SnowflakeTestDatabase, SnowflakeConfigBuilder> {
        protected SnowflakeConfigBuilder(SnowflakeTestDatabase testdb) {
            super(testdb);
        }

    }
}
