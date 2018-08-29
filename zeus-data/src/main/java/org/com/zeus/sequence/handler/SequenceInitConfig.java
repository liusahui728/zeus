package org.com.zeus.sequence.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:SequenceBean.xml" })
public class SequenceInitConfig {

}
