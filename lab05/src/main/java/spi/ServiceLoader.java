package spi;

import ex.api.AnalysisService;

public interface ServiceLoader {
    AnalysisService create();
}
