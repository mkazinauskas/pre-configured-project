package com.projecta.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class IndexController {
    @Value(value = "${sample.property}")
    private String sampleProperty;

    @RequestMapping(method = RequestMethod.GET)
    public String getSampleProperty() {
        return "Sample property from config: ".concat(sampleProperty);
    }
}
