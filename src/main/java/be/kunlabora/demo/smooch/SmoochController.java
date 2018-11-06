package be.kunlabora.demo.smooch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SmoochController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmoochController.class);

    @PostMapping
    public void sendMessage() {
        LOGGER.info("received request to send message");
    }

}
