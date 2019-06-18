package pri.zhenhui.demo.vertx.boot.autoconfigure;

public enum ClusterType {

    /**
     * no cluster
     */
    NONE,
    /**
     * Hazelcast cluster.
     */
    HAZELCAST,

    /**
     * Infinispan cluster.
     */
    INFINISPAN,

    /**
     * Ignite cluster.
     */
    IGNITE,

}
