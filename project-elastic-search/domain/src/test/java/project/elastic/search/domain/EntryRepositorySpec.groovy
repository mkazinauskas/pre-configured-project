package project.elastic.search.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import project.elastic.search.domain.entry.EntryRepository
import project.elastic.search.domain.entry.commands.CreateEntry
import project.elastic.search.domain.entry.commands.CreateEntryHandler
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class EntryRepositorySpec extends Specification {

    @Autowired
    private EntryRepository repository

    @Autowired
    private CreateEntryHandler handler

    def 'should do some stuff'() {
        given:
            String entryId = handler.handle(
                    new CreateEntry(name: 'myName', value: 'myValue')
            )
        expect:
            repository.findByName('myName').first().uniqueId == entryId
    }
}
