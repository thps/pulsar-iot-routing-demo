package org.example;


import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.util.Map;
import java.util.Set;
//import io.lettuce.core.RedisClient;

public class PropFilterFunction implements Function<DeviceMessaage, DeviceMessaage> {
//    private RedisClient redisClient;
//
//    private void initRedisClient(Map<String, Object> connectInfo) {
//        redisClient = RedisClient.create(connectInfo.get("redisURI"));
//    }

    @Override
    public void initialize(Context context) {
//        Map<String, Object> connectInfo = context.getUserConfigMap();
//        redisClient = initRedisClient(connectInfo);
    }

    @Override
    public DeviceMessaage process(DeviceMessaage input, Context context) {
        //String value = client.get(key);
        Map<String, String> props = context.getCurrentRecord().getProperties();
        Set<String> keys = props.keySet();
        if (keys.contains("env") ){
            String envStr = props.get("env");
            context.getLogger().info("  Message has env property: " + new String(props.get("env")));
            //context.publish(context.getOutputTopic() + "-" + envStr, input);
            String prodTopic = "persistent://iot-system/prod/commands";
            String devTopic  = "persistent://iot-system/dev/commands";
            if (envStr.equals("prod")) {
                context.publish(prodTopic, input);
            } else {
                context.publish(devTopic, input);
            }
        } else {
            context.getLogger().info("  Message does not have env property.");
        }
        return input;
    }

//    @Override
//    public void close() {
//        //redisClient.close();
//    }
}
