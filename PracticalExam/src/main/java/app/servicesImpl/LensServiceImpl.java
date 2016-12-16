package app.servicesImpl;

import app.domains.dtos.input.LensesImportJson;
import app.domains.entities.lens.Lens;
import app.parsers.mapper.ModelParser;
import app.repositories.LensRepository;
import app.services.LensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LensServiceImpl implements LensService {

    @Autowired
    private LensRepository lensRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(LensesImportJson lensDto) {

        Lens lens = this.modelParser.convert(lensDto, Lens.class);
        this.lensRepository.saveAndFlush(lens);
    }
}
