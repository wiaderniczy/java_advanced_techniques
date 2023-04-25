package spi;

import java.util.Iterator;
import java.util.ServiceLoader;
import ex.api.AnalysisService;

public class ServiceProvider {
    ServiceLoader<AnalysisService> loader = ServiceLoader.load(AnalysisService.class);

    public Iterator<AnalysisService> providers (boolean refresh){
        if (refresh){
            loader.reload();
        }
        return loader.iterator();
    }
}
