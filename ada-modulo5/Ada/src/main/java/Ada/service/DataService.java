package Ada.service;

import Ada.model.DataDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataDocumentation  dataDocumentationRepository;

    public void saveDocument(List<DataDocumentation> dataDocumentation){
        dataDocumentationRepository.saveAll(dataDocumentation);
    }
}
