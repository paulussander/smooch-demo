package be.kunlabora.demo.smooch.rest;

import be.kunlabora.demo.smooch.client.SmoochClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SmoochController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmoochController.class);

    private final SmoochClient smoochClient;

    public SmoochController(SmoochClient smoochClient) {
        this.smoochClient = smoochClient;
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageTO messageTO) {
        LOGGER.info(
                "received request to send message to {}, with message {}",
                messageTO.getReceiver(),
                messageTO.getMessage()
        );

        smoochClient.sendMessage(messageTO);
    }
}
