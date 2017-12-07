package com.konkerlabs.platform.registry.core.common.type;

public enum EventStorageConfigType {
        MONGODB("mongoEvents"),
        CASSANDRA("cassandraEvents");

        private String bean;

        EventStorageConfigType(String bean) {
            this.bean = bean;
        }

        public String bean(){
            return this.bean;
        }
    }