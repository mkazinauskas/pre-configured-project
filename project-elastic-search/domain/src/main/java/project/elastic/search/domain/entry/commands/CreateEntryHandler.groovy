package project.elastic.search.domain.entry.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import project.elastic.search.domain.entry.Entry
import project.elastic.search.domain.entry.EntryRepository

@Component
class CreateEntryHandler {
    @Autowired
    private EntryRepository repository


    @Transactional
    String handle(CreateEntry createEntry) {
        Entry entry = repository.save(new Entry(createEntry.name, createEntry.value))
        return entry.uniqueId
    }
}
