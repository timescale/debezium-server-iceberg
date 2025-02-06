# Examples

## Verification
### PG/TSDB
Connect to the Postgres/Timescale source instance.
```bash
psql postgres://postgres:postgres@localhost
```

Fetch all of the data from the orders table.
```sql
select * from inventory.orders;
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
