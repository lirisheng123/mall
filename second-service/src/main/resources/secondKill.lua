-- 进行秒杀
-- 查询出redis中商品的库存信息,判断其是否足够,如果足够,则进行库存的删减,秒杀成功,否则,秒杀失败
-- 写入 Redis 的操作用 Lua 脚本来完成，利用 Redis 的单线程机制可以保证每个 Redis 请求的原子性

-- 获取商品的id值
local key = KEYS[1]

redis.log(redis.LOG_DEBUG,tostring("lirisheng:key")..tostring(key))

-- 获取当前的商品库存
local count = tonumber(redis.call('get', tostring("stock_count_")..tostring(key) ))

redis.log(redis.LOG_DEBUG,tostring("lirisheng:count")..tostring(count))

if count == 0 then
    -- 库存为0,秒杀失败
    return tostring("")
else
    -- redis中的count-1
    redis.call("decr", tostring("stock_count_")..tostring(key))
    -- redis中的sale+1
    redis.call("incr", tostring("stock_sale_")..tostring(key))
    return  tostring("stock_name_")..tostring(key)
end