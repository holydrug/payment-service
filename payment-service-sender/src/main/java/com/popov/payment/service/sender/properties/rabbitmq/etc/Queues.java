package com.popov.payment.service.sender.properties.rabbitmq.etc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Queues {
    private String internal;
    private String feedback;
}
