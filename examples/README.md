# Examples

## AWS TableBucket Integration
From the root of this repo:
```sh
# Build package.
mvn clean package -Passembly -Dmaven.test.skip

# Unzip package for local use.
unzip -o debezium-server-iceberg-dist/target/debezium-server-iceberg-dist*.zip -d appdist

# Copy over conf.
cp examples/conf/application.properties appdist/debezium-server-iceberg/conf/

cd appdist/debezium-server-iceberg && ./run.sh
```

### Some Useful Commands
```sh
# Delete a table form a TableBucket.
aws s3tables delete-table --table-bucket-arn arn:aws:s3tables:us-east-1:ACCOUNT:bucket/TableBucketName --namespace icebergdata --name debezium_offset_storage_table
```

## Verification
### PG/TSDB
Connect to the Postgres/Timescale source instance.
```bash
psql postgres://postgres:postgres@localhost
```

Fetch all of the data from the orders table.
```sql
select * from inventory.orders;

-- Setup a new hypertable.
CREATE TABLE conditions (time TIMESTAMPTZ NOT NULL, location TEXT NOT NULL, temperature DOUBLE PRECISION NULL, humidity DOUBLE PRECISION NULL);
SELECT create_hypertable('conditions', 'time');
INSERT INTO conditions VALUES(NOW(), 'San Antonio', 22.8,  53.3);
```

### Spark-SQL
Connect to the spark-sql client.
```bash
docker exec -it iceberg-spark spark-sql
```

Fetch all of the data from the orders table according to the latest snapshot.
```sql
select * from icebergdata.debeziumcdc_dbz__inventory_orders;
select * from icebergdata.debeziumcdc_dbz___timescaledb_internal_bgw_job_stat;
select * from icebergdata.debeziumcdc_dbz___timescaledb_internal__hyper_1_1_chunk;
```
