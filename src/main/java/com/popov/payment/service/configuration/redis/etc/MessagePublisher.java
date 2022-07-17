package com.popov.payment.service.configuration.redis.etc;

public interface MessagePublisher {
    void publish(String message);
}
