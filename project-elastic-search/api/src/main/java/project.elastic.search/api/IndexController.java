package projecta.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getSampleProperty() {
        return ResponseEntity.ok(samplePropertyAsString);
    }
}
