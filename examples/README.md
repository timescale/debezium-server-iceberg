# Examples

## Verification
### PG/TSDB
```bash
# Connect to the Postgres/Timescale source instance.
psql postgres://postgres:postgres@localhost
```

```sql
-- Fetch all data from the orders table.
select * from inventory.orders;

-- Setup a new hypertable.
CREATE TABLE conditions (time TIMESTAMPTZ NOT NULL, location TEXT NOT NULL, temperature DOUBLE PRECISION NULL, humidity DOUBLE PRECISION NULL);
SELECT create_hypertable('conditions', 'time');
INSERT INTO conditions VALUES(NOW(), 'San Antonio', 22.8,  53.3);
```

### Spark-SQL
Connect to the spark-sql client.
```bash
docker exec -it spark spark-sql
```

Fetch all of the data from the orders table according to the latest snapshot.
```sql
select * from icebergdata.debeziumcdc_dbz__inventory_orders;
select * from icebergdata.debeziumcdc_dbz___timescaledb_internal_bgw_job_stat;
select * from icebergdata.debeziumcdc_dbz___timescaledb_internal__hyper_1_1_chunk;
```
