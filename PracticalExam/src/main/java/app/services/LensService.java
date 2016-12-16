package app.services;

import app.domains.dtos.input.LensesImportJson;

public interface LensService {

    void create(LensesImportJson lens);
}
