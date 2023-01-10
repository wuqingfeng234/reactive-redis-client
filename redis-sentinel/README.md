# redis-sentinel
conenct to a redis cluster guarded by sentinel
# 一主二从三sentinel配置
master:127.0.0.1:6379
slave1:127.0.0.1:6380
slave2:127.0.0.1:6381
sentinel1:127.0.0.1:26379
sentinel2:127.0.0.1:26479
sentinel3:127.0.0.1:26579
name of master redis node guarded by sentinel:mymaster