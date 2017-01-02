package project.elastic.search.elastic.search.elastic.search.domain.entry.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entries
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entry

@Component
class CreateEntryHandler {
    @Autowired
    private Entries repository


    @Transactional
    String handle(CreateEntry createEntry) {
        Entry entry = repository.save(new Entry(createEntry.name, createEntry.value))
        return entry.uniqueId
    }
}
