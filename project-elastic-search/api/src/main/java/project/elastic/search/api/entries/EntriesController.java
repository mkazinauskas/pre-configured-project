package project.elastic.search.api.entries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entry;
import project.elastic.search.elastic.search.elastic.search.domain.entry.EntryRepository;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/entries")
public class EntriesController {

    @Autowired
    private EntryRepository entries;

    @Autowired
    private EntryBeanMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resource<Page<EntryBean>>> entries(Pageable pageable) {
        Page<Entry> foundEntries = entries.findAll(pageable);
        return ResponseEntity.ok(new Resource<>(
                        foundEntries.map(mapper::map),
                linkTo(methodOn(EntriesController.class).entries(null)).withSelfRel()
        ));
    }


}
