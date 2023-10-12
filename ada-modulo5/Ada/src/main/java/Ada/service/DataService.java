package Ada.service;

import Ada.model.DataDocumentation;
import Ada.repository.DataDocumentationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private final DataDocumentationRepository dataDocumentationRepository;

    private DataService(DataDocumentationRepository dataDocumentationRepository) {
        this.dataDocumentationRepository = dataDocumentationRepository;
    }

    public void saveDocument(List<DataDocumentation> dataDocumentation) {
        dataDocumentationRepository.saveAll(dataDocumentation);
    }
}
