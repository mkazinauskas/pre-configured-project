package project.elastic.search.api;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.elastic.search.api.entries.EntriesController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> index() {
        ResourceSupport response = new ResourceSupport();
        response.add(linkTo(methodOn(IndexController.class).index()).withSelfRel());
        response.add(linkTo(methodOn(EntriesController.class)
                .entries(null))
                .withRel("entries")
        );
        return ResponseEntity.ok(response);
    }


}
