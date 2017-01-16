package project.elastic.search.elastic.search.elastic.search.domain.entry.commands.delete

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entries
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entry

@Component
class DeleteEntryHandler {
    @Autowired
    private Entries repository

    @Autowired
    private DeleteEntryValidator validator

    @Transactional
    void handle(DeleteEntry command) {
        validator.validate(command)
        Entry entry = repository.findByUniqueId(command.uniqueId)
        repository.delete(entry)
    }
}
